package ru.origami.sample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.origami.sample.models.NotifyMessage;
import ru.origami.sample.models.RequestDto;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessageForNewRequest(RequestDto request) {
        NotifyMessage message = new NotifyMessage()
                .setHeader("Новый запрос")
                .setMessage(request.getName());
        messagingTemplate.convertAndSend("/topic/notify", message);
    }

    public void sendMessageForChangeRequest(RequestDto request) {
        NotifyMessage message = new NotifyMessage()
                .setHeader("Обновлен запрос")
                .setMessage(request.getName());
        messagingTemplate.convertAndSend("/topic/notify", message);
    }
}
