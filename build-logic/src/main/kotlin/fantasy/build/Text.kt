package fantasy.build

enum class TextStyle(val code: Char, val color: Boolean = false) {
    BLACK('0', true), DARK_BLUE('1', true), DARK_GREEN('2', true), DARK_AQUA('3', true),
    DARK_RED('4', true), DARK_PURPLE('5', true), GOLD('6', true), GRAY('7', true),
    DARK_GRAY('8', true), BLUE('9', true), GREEN('a', true), AQUA('b', true),
    RED('c', true), LIGHT_PURPLE('d', true), YELLOW('e', true), WHITE('f', true),
    OBFUSCATED('k'), BOLD('l'), STRIKETHROUGH('m'), UNDERLINE('n'), ITALIC('o'),
}

/** Scoped formatting restores the parent style, including color and decorations. */
class StyledText internal constructor(private val styles: List<TextStyle> = emptyList()) {
    private val output = StringBuilder()
    operator fun String.unaryPlus() {
        require('§' !in this) { "Use styled() inside text {}; literal section codes are supported by entry(key, string)." }
        output.append(this)
    }
    fun styled(vararg additions: TextStyle, configure: StyledText.() -> Unit) {
        val color = additions.lastOrNull { it.color } ?: styles.lastOrNull { it.color }
        val formats = (styles + additions).filterNot { it.color }.distinct()
        val effective = listOfNotNull(color) + formats
        output.append(resetTo(effective))
        output.append(StyledText(effective).apply(configure).build())
        output.append(resetTo(styles))
    }
    internal fun build(): String = output.toString()
    private fun resetTo(styles: List<TextStyle>) = "§r" + styles.joinToString("") { "§${it.code}" }
}

fun text(configure: StyledText.() -> Unit): String = StyledText().apply(configure).build()

/** Fabric tooltips render explicit lines independently, so each needs its active section codes. */
fun repeatFormattingAfterNewlines(text: String): String = buildString {
    var color: Char? = null
    val decorations = linkedSetOf<Char>()
    var index = 0
    while (index < text.length) {
        val character = text[index++]
        append(character)
        if (character == '§' && index < text.length) {
            val code = text[index++].also { append(it) }.lowercaseChar()
            when (code) {
                in '0'..'9', in 'a'..'f' -> {
                    color = code
                    decorations.clear()
                }
                in 'k'..'o' -> decorations.add(code)
                'r' -> {
                    color = null
                    decorations.clear()
                }
            }
        } else if (character == '\n' && index < text.length) {
            color?.let { append('§').append(it) }
            decorations.forEach { append('§').append(it) }
        }
    }
}
