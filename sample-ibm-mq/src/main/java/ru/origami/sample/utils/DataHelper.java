package ru.origami.sample.utils;

import lombok.experimental.UtilityClass;
import ru.origami.common.OrigamiHelper;
import ru.origami.sample.models.ibm_mq.RequestMessage;

@UtilityClass
public class DataHelper {

    public static RequestMessage getRequestMessage() {
        return new RequestMessage()
                .setId(OrigamiHelper.getUUID())
                .setName(OrigamiHelper.getRandomCyrillicString(5))
                .setNote(OrigamiHelper.getRandomLatinString(10));
    }
}
