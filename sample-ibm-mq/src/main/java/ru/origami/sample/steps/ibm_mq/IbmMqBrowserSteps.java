package ru.origami.sample.steps.ibm_mq;

import ru.origami.common.environment.Environment;
import ru.origami.ibm_mq.IbmMqBrowser;
import ru.origami.ibm_mq.models.Properties;

public class IbmMqBrowserSteps extends IbmMqBrowser {

    public IbmMqBrowserSteps() {
        properties = Properties.Builder()
                .setHost(Environment.get("ibm.mq.host"))
                .setPort(Environment.getInt("ibm.mq.port"))
                .setQueueManagerName(Environment.get("ibm.mq.queue.manager"))
                .setChannel(Environment.get("ibm.mq.channel"))
                .setUsername(Environment.get("ibm.mq.username"))
                .setPassword(Environment.get("ibm.mq.password"))
                .build();
    }
}
