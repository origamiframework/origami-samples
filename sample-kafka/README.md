<br>
<div align="center">
<img src="./src/main/resources/img/logo.png" alt="Origami" width="200">

# Kafka sample
</div>

В данном примере используется взаимодействие с топиками Kafka
- отправка сообщений в топик
- отправка сообщения в нужную партицию
- вычитка топика на всю глубину
- подписка на топик

Полное руководство можно найти в [документации](https://origamiframework.ru/docs.html#origami-kafka).

Для взаимодействия с Kafka используется модуль origami-framework-kafka.

Для запуска тестов требуется сервер Kafka, который поднимается благодаря использованию модуля origami-framework-test-containers.
Необходим Docker с Engine ниже версии v29.0.0. На текущий момент последняя 
актуальная версия [Docker Desktop](https://docs.docker.com/desktop/release-notes/#4510) с Docker Engine v28.5.2

## Описание

В пакете models.kafka содержатся классы, который описывают сообщения в топиках, а так же Enum с содержанием названий топиков.

В пакете steps.kafka содержатся классы для Producer и Consumer с описанием настроек подключения. 
А так же класс, содержащий проверочные методы.

В пакете utils содержатся хелперы, а так же класс описывающий Test Container.

В resources.config содержатся json с настройками для конкретного стенда.


## Запуск тестов

Для запуска тестов с использованием Test Containers используется команда:
```bash
  mvn test -Dtest.containers.enabled=true
```