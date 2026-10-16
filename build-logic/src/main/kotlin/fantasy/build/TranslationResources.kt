package fantasy.build

internal fun translationResources(languages: Map<String, Map<String, String>>): Map<String, String> =
    languages.mapKeys { (locale, _) -> "assets/$RESOURCE_NAMESPACE/lang/$locale.json" }
        .mapValues { (_, entries) -> json(entries) }
