package gui;

import javafx.event.Event;
import javafx.event.EventType;

public class NavigateEvent extends Event {
    public static final EventType<NavigateEvent> NAVIGATE = new EventType<>(Event.ANY, "NAVIGATE");

    private final String destination;

    public NavigateEvent(String destination) {
        super(NAVIGATE);
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }
}
