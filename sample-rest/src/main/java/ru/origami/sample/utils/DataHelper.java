package ru.origami.sample.utils;

import lombok.experimental.UtilityClass;
import ru.origami.common.OrigamiHelper;
import ru.origami.sample.models.RequestModel;

@UtilityClass
public class DataHelper {

    public static RequestModel getRequest() {
        return new RequestModel()
                .setName(OrigamiHelper.getRandomCyrillicString(5))
                .setNote(OrigamiHelper.getRandomLatinString(10));
    }
}
