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
public class DataSynchronizedWatcher {

    private static final Logger LOG = LoggerFactory.getLogger(DataSynchronizedWatcher.class);

    private boolean isDataSynchronized = false;
    private Date lastDataSynchronization = null;

    @EventListener
    public void markDataSynchronized(DataSynchronizedEvent event) {
        LOG.info("> Marking data as synchronized, date [{}]", event.getSyncDate());
        isDataSynchronized = true;
        lastDataSynchronization = event.getSyncDate();
    }

    public boolean isDataSynchronized() {
        return isDataSynchronized;
    }

    public Date getLastDataSynchronization() {
        return lastDataSynchronization;
    }
}
