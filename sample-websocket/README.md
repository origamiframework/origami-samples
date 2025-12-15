<br>
<div align="center">
<img src="../src/main/resources/img/logo.png" alt="Origami" width="200">

# WebSocket sample
</div>

В данном примере описано взаимодействие по WebSocket.

Полное руководство можно найти в [документации](https://origamiframework.ru/docs.html#origami-websocket).

Для реализации используется модуль <i>origami-framework-websocket</i>.

Для запуска тестов требуется сервис Requests Service. Он реализован в модуле <i>requests-service</i>.
Для запуска сервиса можно использовать класс RequestServiceApplication.
Либо использовать TestContainers, которые поднимаются благодаря использованию модуля <i>origami-framework-test-containers</i>.
Необходим Docker с Engine ниже версии v29.0.0. На текущий момент последняя 
актуальная версия [Docker Desktop](https://docs.docker.com/desktop/release-notes/#4510) с Docker Engine v28.5.2.

## Описание

В пакете <i>models</i> содержатся классы, которые описывают сообщения.

В пакете <i>steps.requests_service</i> содержится описание шагов для взаимодействия с сервисом(post, get запросы), а так же методы для проверки.

В пакете <i>steps.ws</i> содержатся классы с описанием настроек подключения по WebSocket. А так же проверочные методы.

В пакете <i>utils</i> содержатся хелперы, а так же класс описывающий Test Container.

В <i>resources.config</i> содержатся json с настройками для конкретного стенда.

В <i>resources.statics.routs.json</i> содержится описание методов сервиса.


## Сборка docker image сервиса

Необходимо перейти в модуль <i>requests-service</i> и выполнить в консоли
```bash
  mvn clean install
```

Затем выполнить в консоли
```bash
  docker build -f Dockerfile -t requests-service .
```


## Запуск тестов

Для запуска тестов с использованием Test Containers используется команда:
```bash
  mvn test -Dtest.containers.enabled=true -Dstand=test_containers
```