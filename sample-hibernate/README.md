<br>
<div align="center">
<img src="../src/main/resources/img/logo.png" alt="Origami" width="200">

# Hibernate sample
</div>

В данном примере используется взаимодействие с базой данных postgres.

Полное руководство можно найти в [документации](https://origamiframework.ru/docs.html#origami-hibernate).

Для взаимодействия с базой данных используется модуль <i>origami-framework-hibernate</i>.

Для запуска тестов требуется база данных postgres, которая поднимается благодаря использованию модуля <i>origami-framework-test-containers</i>.
Необходим Docker с Engine ниже версии v29.0.0. На текущий момент последняя 
актуальная версия [Docker Desktop](https://docs.docker.com/desktop/release-notes/#4510) с Docker Engine v28.5.2.

## Описание

В пакете <i>models.kafka</i> содержатся классы, которые описывают сообщения в топиках, а так же Enum с содержанием названий топиков.

В классе <i>steps.BeforeAllTestsExtension</i> содержится логика создания таблицы базы данных. Данная логика 
должна быть реализована в самом сервисе, а не в тестовом репозитории.

В классе <i>steps.MyServiceFixtureSteps</i> реализованы методы вычитки из базы данных(SELECT), 
а так же методы создания и изменения данных в базе данных.

В пакете <i>utils</i> содержатся хелперы, а так же класс описывающий Test Container.

В <i>resources.config</i> содержатся json с настройками для конкретного стенда.


## Запуск тестов

Для запуска тестов с использованием Test Containers используется команда:
```bash
  mvn test -Dtest.containers.enabled=true
```