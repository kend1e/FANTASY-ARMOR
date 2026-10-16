# Fantasy Armor

Один Gradle multi-project build для Forge, NeoForge и Fabric. Импортируйте
`settings.gradle.kts` в IntelliJ IDEA. Для запуска Gradle используйте JDK 21;
Java toolchains выбирают Java 17 или 21 для платформенных исходников.

```bash
./gradlew assemble
./gradlew :platforms:fabric-1.21.1:assemble
./gradlew :platforms:forge-1.21.11:runClient
./gradlew generateAll
```

Готовые устанавливаемые моды находятся в `dist/`. `generateAll` подготавливает
ресурсы; перед сборкой или Gradle dev-запуском вызывать его вручную не нужно.

- [Структура, сборка и добавление контента](docs/architecture.md)
- [Состояние миграции и следующие шаги](docs/migration.md)
- [Определения комплектов](definitions/armor.kt)
- [Поддерживаемые цели и версии зависимостей](definitions/targets.kt)
- [Переводы](definitions/translations.kt)

В `README/5-neoforge-api-changes.md` и `README/6-fabric-api-changes.md` сохранены
исторические заметки об API. Актуальные инструкции находятся в `docs/`.
