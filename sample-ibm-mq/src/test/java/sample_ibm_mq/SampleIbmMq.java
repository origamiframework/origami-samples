package sample_ibm_mq;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.origami.common.OrigamiHelper;
import ru.origami.sample.models.ibm_mq.RequestMessage;
import ru.origami.sample.steps.ibm_mq.IbmMqBrowserSteps;
import ru.origami.sample.steps.ibm_mq.IbmMqConsumerSteps;
import ru.origami.sample.steps.ibm_mq.IbmMqProducerSteps;
import ru.origami.sample.steps.ibm_mq.IbmMqSteps;
import ru.origami.sample.utils.DataHelper;
import ru.origami.testit_allure.allure.java_commons.Feature;
import ru.origami.testit_allure.annotations.Description;
import ru.origami.testit_allure.annotations.Link;
import ru.origami.testit_allure.annotations.Story;

import javax.jms.Message;

import java.util.List;

import static ru.origami.sample.models.ibm_mq.EQueue.REQUEST_QUEUE;

@Feature("Реализация работы с IBM MQ")
@Story("Работа с топиками IBM MQ")
@DisplayName("Отправка и чтение сообщений из очереди")
@Link(name = "Документация", url = "https://example.ru/confluence/pages/viewpage.action?pageId=123")
public class SampleIbmMq {

    IbmMqBrowserSteps browserSteps = new IbmMqBrowserSteps();

    IbmMqConsumerSteps consumerSteps = new IbmMqConsumerSteps();

    IbmMqProducerSteps producerSteps = new IbmMqProducerSteps();

    IbmMqSteps ibmMqSteps = new IbmMqSteps();

    RequestMessage request;

    @BeforeAll
    @DisplayName("Добавление заявки в очередь")
    void createMessage() {
        request = DataHelper.getRequestMessage();
        String message = OrigamiHelper.getObjectAsJsonString(request);

        producerSteps.send(REQUEST_QUEUE, message);
    }

    @Test
    @DisplayName("Отправка сообщения")
    @Description("Пример отправки сообщения")
    void successProducer() {
        RequestMessage request = DataHelper.getRequestMessage();
        String message = OrigamiHelper.getObjectAsJsonString(request);

        producerSteps.send(REQUEST_QUEUE, message);
    }

    @Test
    @DisplayName("Вычитка сообщения с помощью Consumer")
    @Description("Пример вычитки сообщения")
    void successConsumer() {
        Message readedMessage = consumerSteps.read(REQUEST_QUEUE);
        ibmMqSteps.requestsShouldBeEquals(request, List.of(readedMessage));
    }

    @Test
    @DisplayName("Вычитка сообщения с помощью Browser")
    @Description("Пример вычитки сообщения")
    void successBrowser() {
        RequestMessage request = DataHelper.getRequestMessage();
        String message = OrigamiHelper.getObjectAsJsonString(request);

        producerSteps.send(REQUEST_QUEUE, message);

        List<Message> readMessages = browserSteps.read(REQUEST_QUEUE);
        ibmMqSteps.requestsShouldBeEquals(request, readMessages);
    }
}