package org.spring.events.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author kedzierm
 */
@Component
public class DataSynchronizer {

    private static final Logger LOG = LoggerFactory.getLogger(DataSynchronizer.class);

    @Autowired
    private ApplicationEventPublisher publisher;

    public void synchronizeData() {

        LOG.debug("> Synchronizing data...");
        heavyAction();
        LOG.debug("< Synchronized.");

        // when data are synchronized few another things must happen

        publisher.publishEvent(new CustomEvent(LocalDateTime.now()));

    }

    private void heavyAction() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            LOG.error("Thread interrupted", e);
        }
    }

}
