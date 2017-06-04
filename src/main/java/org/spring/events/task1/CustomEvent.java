package org.spring.events.task1;

import java.time.LocalDateTime;

public class CustomEvent {

    private LocalDateTime date;

    public CustomEvent(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
