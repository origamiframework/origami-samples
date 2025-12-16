<br>
<div align="center">
<img src="../src/main/resources/img/logo.png" alt="Origami" width="200">

# IBM MQ sample
</div>

В данном примере используется взаимодействие с очередями IBM MQ:
- отправка сообщений в очередь
- вычитка сообщений с помощью Consumer
- вычитка сообщений с помощью Browser

Полное руководство можно найти в [документации](https://origamiframework.ru/docs.html#origami-ibm-mq).

Для взаимодействия с Kafka используется модуль <i>origami-framework-ibm-mq</i>.

Для запуска тестов требуется сервер IBM MQ, который поднимается благодаря использованию модуля <i>origami-framework-test-containers</i>.
Необходим Docker с Engine ниже версии v29.0.0. На текущий момент последняя 
актуальная версия [Docker Desktop](https://docs.docker.com/desktop/release-notes/#4510) с Docker Engine v28.5.2.

## Описание

В пакете <i>models.ibm_mq</i> содержатся классы, которые описывают сообщения в очередях, а так же Enum с содержанием названий очередей.

В пакете <i>steps.ibm_mq</i> содержатся классы для Producer, Consumer и Browser с описанием настроек подключения. 
А так же класс, содержащий проверочные методы.

В пакете <i>utils</i> содержатся хелперы, а так же класс описывающий Test Container.

В <i>resources.config</i> содержатся json с настройками для конкретного стенда.


## Запуск тестов

Для запуска тестов с использованием Test Containers используется команда:
```bash
  mvn test -Dtest.containers.enabled=true
```