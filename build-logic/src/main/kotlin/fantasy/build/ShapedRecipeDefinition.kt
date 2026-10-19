package fantasy.build

data class ShapedRecipeDefinition(
    val id: String,
    val pattern: List<String>,
    val ingredients: Map<String, String>,
    val result: String,
) {
    init {
        require(id.matches(Regex("[a-z0-9_]+"))) { "Invalid recipe ID: $id" }
        require(pattern.size in 1..3 && pattern.all { it.length in 1..3 && it.length == pattern.first().length }) {
            "Recipe $id must have a rectangular pattern of at most 3 x 3"
        }
        val symbols = pattern.joinToString("").filter { it != ' ' }.map(Char::toString).toSet()
        require(symbols.isNotEmpty() && symbols == ingredients.keys) { "Recipe $id has mismatched ingredient keys" }
    }
}
