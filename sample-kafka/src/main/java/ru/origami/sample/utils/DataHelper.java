package ru.origami.sample.utils;

import lombok.experimental.UtilityClass;
import ru.origami.common.OrigamiHelper;
import ru.origami.sample.models.kafka.RequestTopicMessage;

@UtilityClass
public class DataHelper {

    public static RequestTopicMessage getRequestTopicMessage() {
        return new RequestTopicMessage()
                .setId(OrigamiHelper.getUUID())
                .setName(OrigamiHelper.getRandomCyrillicString(5))
                .setNote(OrigamiHelper.getRandomLatinString(10));
    }
}
