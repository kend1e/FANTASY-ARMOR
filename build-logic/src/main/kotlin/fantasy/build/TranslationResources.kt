package fantasy.build

internal fun translationResources(
    languages: Map<String, Map<String, String>>,
    loader: Loader,
): Map<String, String> = buildMap {
    for ((locale, entries) in languages) {
        val translations = if (loader == Loader.FABRIC) {
            entries.mapValues { (_, value) -> repeatFormattingAfterNewlines(value) }
        } else entries
        put("assets/$RESOURCE_NAMESPACE/lang/$locale.json", json(translations))
    }
}
