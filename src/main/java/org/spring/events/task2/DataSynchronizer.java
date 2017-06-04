package org.spring.events.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author kedzierm
 */
@Component
public class DataSynchronizer {

    private static final Logger LOG = LoggerFactory.getLogger(DataSynchronizer.class);

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Scheduled(cron = "0/3 * * * * ?")
    public void synchronizeData() {

        LOG.debug("> Synchronizing data...");
        heavyAction();
        LOG.debug("< Synchronized.");

        // when data are synchronized few another things must happen
       eventPublisher.publishEvent(new DataSynchronizedEvent(new Date()));


    }

    private void heavyAction() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            LOG.error("Thread interrupted", e);
        }
    }

}
