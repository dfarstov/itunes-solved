Простой аналог музыкального сервиса. Управление артистами, альбомами, композициями и плейлистами из их. 
Для примера уже реализован CRUD (добавление, редактирование, обновление и удаление)
пользователей и плейлистов (без песен). 

### Тестовое задание
- Реализовать CRUD для моделей артистов, альбомов и песен (с валидацией необходимых полей)
- Добавить параметризированный поиск (используя Criteria API): 
  - исполнителей по названию и жанру
  - альбомов по названию, жанру, исполнителю
  - песен по названию, жанру, исполнителю, альбому
- Связать песни с плейлистами (у одной песни может быть несколько плейлистов)
- Расширить параметризацию поиска плейлистов поиском по исполнителю/альбому/песне

#### Артист:
- Первичный ключ
- Название
- Список альбомов

#### Альбом
- Первичный ключ
- Название
- Дата релиза
- Жанр
- Артист
- Список песен

#### Песня
- Первичный ключ
- Название
- Длительность
- Альбом

Реализация CRUD представляет собой собой REST API, 
который оформлен согласно общепринятым стандартам.  

### Установка и запуск
- IntelliJ IDEA - https://www.jetbrains.com/ru-ru/idea/
- JDK 8 - https://www.malavida.com/ru/soft/java-jdk/download

После установки JDK убедитесь, что переменная окружения JAVA_HOME указывает на верную директорию. 

После установки IDEA установите плагин lombok https://plugins.jetbrains.com/plugin/6317-lombok

Открыть в IDEA файл build.gradle, заимпортить проект, для запуска использовать таску bootRun.
Аналогично в командной строке можно выполнить ``gradlew bootRun``.

После запуска будет доступен интерфейс Swagger для тестирования API по адресу http://localhost:8080/swagger-ui.html

В случае ошибок также убедитесь, что IDEA использует JDK именно версии 8, а не собственную 11.

В приложении используется встроенная база данных H2, подключать ничего не нужно. 
Файл базы сгенерируется в директории ``out``.

Для генерации геттеров/сеттеров/конструкторов используются аннотации 
lombok (https://projectlombok.org/features/all).
Если у вас триллион ошибок при компиляции - проверьте, 
включен ли ``annotation processing`` в настройках IDEA и установлен ли плагин lombok. 
При импорте редактор сам предложит включить процессинг -> внимание на правый нижний угол редактора.

Для миграций используется liquibase (https://www.liquibase.org/documentation/changes), 
файлы миграции находятся в директории ``src/main/resources/db/include``

- https://www.baeldung.com/persistence-with-spring-series - гайды по работе с Hibernate
- http://java-online.ru/hibernate-entities.xhtml - связи между таблицами в Hibernate
- https://www.baeldung.com/rest-with-spring-series - гайды по работе с REST API
- https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-controller - документация по контроллерам

документация по репозиториям и работе с бд:
- https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.core-concepts - основные принципы
- https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods - кастомные методы и ключевые слова



### Структура проекта
```
├───src
|    └───main
|        ├───java/org/astelit/itunes       
|        │   ├───configuration
|        │   ├───controller
|        │   ├───dto
|        │   ├───entity
|        │   ├───enums
|        │   ├───exception
|        │   ├───repository
|        │   ├───service
|        │   └───utils
|        │
|        └───resources
|            │   application.yaml
|            │   log4j2.yaml
|            │
|            └───db
|                │   changelog.yaml
|                └───include
|
│   build.gradle
│   gradlew
│   gradlew.bat
│   readme.md
│   settings.gradle
```

Общая структура

| название       | описание                                                                                                                                                                                                                              |
|---------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| build.gradle | Скрипт сборки проекта. В нем указываются зависимости, подключаются плагины для дополнительных задач.
| gradlew       | CLI для gradle. Используйте ``gradlew bootRun`` для запуска проекта.
| src/main/java/org/astelit/itunes/| Директория исходного кода|
| src/main/resources|Ресурсы приложения|
| src/main/resources/db|Миграции для базы данных|
| src/main/resources/application.yaml|Конфигурация приложения|
| src/main/resources/log4j2.yaml|Конфигурация логгера приложения|

Структура пакетов приложения

| Пакет         | Описание                                                                                                                                                                                                                              |
|---------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| configuration | Пакет конфигурационных классов, которые считывает Spring при старте приложения                                                                                                                                                               |
| controller    | Пакет для контроллеров приложения                                                                                                                                                                              |
| сonstraint    | Пакет  кастомных аннотаций для валидации полей. |
| dto           | Пакет для классов, описывающих данные для запросов/ответов приложения.                                                                                                                                                                                      |
| entity        | Пакет для классов, которые описывают таблицы базы данных. |
| exceptions    | Пакет для эксепшенов, которые стоит кидать если нужно вернуть ошибку для клиента.                                                                                                                                                                |
| repository    | Пакет для интерфейсов, которые предоставляют набор методов для работы с бд. Spring автоматически реализует эти интерфейсы при старте приложения.<br> |
| service       | Пакет бизнесс-логики приложения. Производите все операции с данными в классах этого пакета.                                                                                                                                      |

### Цепочка зависимостей пакетов внутри приложения
controller <-> dto <-> service <-> entity <-> repository

Цикл разработки: 
- Создание миграции
- Создание entity-класса
- Создание repository класса
- Создание dto классов
- Создание service класса
- Создание controller класса

### Советы
Используйте следующие методы для работы с ресурсами:
- POST - добавление объектов 
- PATCH - обновление объектов 
- GET - получение информации
- DELETE - удаление ресурса

Не возвращайте и не принимайте entity-классы в контроллерах:
- вы получите ошибку при попытке сохранить такой класс в базу данных
- это приведет к рекурсивной загрузке всех LAZY полей в момент сериализации объекта в JSON и последующим ошибкам. 

Используйте классы из пакета dto для работы с контроллерами.  
