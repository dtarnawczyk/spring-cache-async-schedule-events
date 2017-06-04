package org.spring.events.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataSynchronizationNotifier {

    private static final Logger LOG = LoggerFactory.getLogger(DataSynchronizationNotifier.class);

    @EventListener
    public void sendNotification(CustomEvent event) {
        String notificationContent = prepareContent(event.getDate());
        LOG.info("> Sending data synchronized notification: [{}]", notificationContent);

    }

    private String prepareContent(LocalDateTime date) {
        return "Data synchronized on " + date;
    }

}
