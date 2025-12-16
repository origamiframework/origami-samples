package ru.origami.sample.steps.ibm_mq;

import ru.origami.common.OrigamiHelper;
import ru.origami.ibm_mq.utils.MessageHelper;
import ru.origami.sample.models.ibm_mq.RequestMessage;
import ru.origami.testit_allure.annotations.Step;

import javax.jms.Message;
import java.util.List;

import static ru.origami.common.asserts.Asserts.*;

public class IbmMqSteps {

    @Step("Проверяем полученные данные")
    public void requestsShouldBeEquals(RequestMessage expected, List<Message> actualList) {
        RequestMessage actualMessage = actualList.stream()
                .map(m -> OrigamiHelper.getObjectFromJson(MessageHelper.getMessageBody(m), RequestMessage.class))
                .filter(r -> r.getId().equals(expected.getId()))
                .findFirst()
                .orElse(null);

        assertAll(
                () -> assertTrue("messages size", 1 <= actualList.size()),
                () -> assertEquals("id", expected.getId(), actualMessage.getId()),
                () -> assertEquals("name", expected.getName(), actualMessage.getName()),
                () -> assertEquals("note", expected.getNote(), actualMessage.getNote())
        );
    }
}
