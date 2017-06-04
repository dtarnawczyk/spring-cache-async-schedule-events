package org.spring.events.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author kedzierm
 */
@Component
public class DataSynchronizedWatcher {

    private static final Logger LOG = LoggerFactory.getLogger(DataSynchronizedWatcher.class);

    private boolean isDataSynchronized = false;
    private LocalDateTime lastDataSynchronization = null;

    @EventListener
    public void markDataSynchronized(CustomEvent event) {
        LOG.info("> Marking data as synchronized");
        isDataSynchronized = true;
        lastDataSynchronization = event.getDate();
    }

    public boolean isDataSynchronized() {
        return isDataSynchronized;
    }

    public LocalDateTime getLastDataSynchronization() {
        return lastDataSynchronization;
    }
}
