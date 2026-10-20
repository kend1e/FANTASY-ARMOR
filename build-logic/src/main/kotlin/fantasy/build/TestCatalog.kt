package fantasy.build

data class TestCase(
    val id: String,
    val owner: String,
    val minecraft: Set<String> = emptySet(),
    val loaders: Set<Loader> = emptySet(),
) {
    init {
        require(id.matches(Regex("[a-z][a-z0-9_]*"))) { "Invalid test id: $id" }
        require(owner.matches(Regex("[A-Z][A-Za-z0-9]*"))) { "Invalid test owner: $owner" }
    }
    fun appliesTo(target: Target) = (minecraft.isEmpty() || target.minecraft in minecraft) &&
        (loaders.isEmpty() || target.loader in loaders)
}

enum class TestRegistration {
    FORGE_CONTAINER, FORGE_COMPONENTS, NEOFORGE, FABRIC_VANILLA, FORGE_FUNCTIONS, FABRIC_REGISTRY;

    fun annotation(): String = when (this) {
        FORGE_FUNCTIONS -> error("Forge function tests do not use annotations")
        FABRIC_REGISTRY -> "@net.fabricmc.fabric.api.gametest.v1.GameTest(maxTicks = 100)"
        NEOFORGE -> "@GameTest(template = \"empty\", templateNamespace = \"fantasy_armor_tests\")"
        FORGE_CONTAINER -> "@GameTest(template = \"empty\", templateNamespace = \"fantasy_armor_tests\")"
        FORGE_COMPONENTS -> "@GameTest(template = \"fantasy_armor_tests:empty\")"
        FABRIC_VANILLA -> "@GameTest(template = \"fabric-gametest-api-v1:empty\")"
    }

    fun holder(): String = when (this) {
        FORGE_CONTAINER -> "@net.minecraftforge.gametest.GameTestHolder(\"fantasy_armor_tests\")\n@net.minecraftforge.gametest.PrefixGameTestTemplate(false)"
        FORGE_COMPONENTS -> "@net.minecraftforge.gametest.GameTestHolder(\"fantasy_armor_tests\")"
        NEOFORGE -> "@net.neoforged.neoforge.gametest.GameTestHolder(\"fantasy_armor_tests\")\n@net.neoforged.neoforge.gametest.PrefixGameTestTemplate(false)"
        else -> ""
    }

    fun reportName(id: String): String = when (this) {
        FORGE_CONTAINER, NEOFORGE -> id
        FORGE_COMPONENTS -> "fantasy_armor_tests.$id"
        FABRIC_VANILLA -> "armortests.$id"
        FABRIC_REGISTRY -> "fantasy_armor_tests:armor_tests_$id"
        FORGE_FUNCTIONS -> "fantasy_armor_tests:$id"
    }

    val fabric get() = this in setOf(FABRIC_VANILLA, FABRIC_REGISTRY)

    val additionalReportNames: Set<String> get() = when (this) {
        FORGE_FUNCTIONS, FABRIC_REGISTRY -> setOf("minecraft:always_pass")
        else -> emptySet()
    }

    fun entrypoint(cases: List<TestCase>): String {
        require(cases.isNotEmpty() && cases.map { it.id }.distinct().size == cases.size) { "Test IDs must be nonempty and unique" }
        if (this == FORGE_FUNCTIONS) return buildString {
            appendLine("package net.kenddie.fantasyarmor.test;")
            appendLine()
            appendLine("import net.minecraft.gametest.framework.GameTestHelper;")
            appendLine("import net.minecraft.core.registries.Registries;")
            appendLine("import net.minecraftforge.registries.DeferredRegister;")
            appendLine("import net.minecraftforge.eventbus.api.bus.BusGroup;")
            appendLine("import java.util.function.Consumer;")
            appendLine()
            appendLine("public final class ArmorTests {")
            appendLine("    private static final DeferredRegister<Consumer<GameTestHelper>> FUNCTIONS =")
            appendLine("            DeferredRegister.create(Registries.TEST_FUNCTION, \"fantasy_armor_tests\");")
            appendLine("    static {")
            cases.forEach { case ->
                appendLine("        FUNCTIONS.register(\"${case.id}\", () -> ${case.owner}::run);")
            }
            appendLine("    }")
            appendLine("    public static void register(BusGroup bus) { FUNCTIONS.register(bus); }")
            appendLine("    private ArmorTests() {}")
            appendLine("}")
        }
        return buildString {
            appendLine("package net.kenddie.fantasyarmor.test;")
            appendLine()
            if (this@TestRegistration != FABRIC_REGISTRY) appendLine("import net.minecraft.gametest.framework.GameTest;")
            appendLine("import net.minecraft.gametest.framework.GameTestHelper;")
            appendLine()
            if (holder().isNotEmpty()) appendLine(holder())
            appendLine("public final class ArmorTests {")
            cases.forEach { case ->
                appendLine("    ${annotation()}")
                appendLine("    public ${if (fabric) "" else "static "}void ${case.id}(GameTestHelper helper) {")
                appendLine("        ${case.owner}.run(helper);")
                appendLine("    }")
                appendLine()
            }
            appendLine("}")
        }
    }
}

data class TestVersion(
    val sources: List<String>,
    val packFormat: Int,
    val structures: String,
    val registrations: Map<Loader, TestRegistration>,
    val serverConfigDirectory: String = "world/serverconfig",
)

