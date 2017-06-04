package org.spring.events.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author kedzierm
 */
@Component
public class DataSynchronizationNotifier {

    private static final Logger LOG = LoggerFactory.getLogger(DataSynchronizationNotifier.class);

    @EventListener(condition = "#event.syncDate.getTime() % 2 == 0")
    public void sendNotification(DataSynchronizedEvent event) {
        String notificationContent = prepareContent(event.getSyncDate());
        LOG.info("> Sending data synchronized notification: [{}]", notificationContent);

    }

    private String prepareContent(Date syncDate) {
        return "Data synchronized on " + syncDate;
    }

}
