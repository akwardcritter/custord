package org.openjfx.dbi.util;

import javafx.event.Event;
import javafx.event.EventType;

public class MutationEvent extends Event {

    public enum MutationType {
        ADD, DELETE, UPDATE
    }

    private final MutationType mutationType;

    public static final EventType<MutationEvent> MUTATION = new EventType<>(Event.ANY,
            "CUSTOMER_MUTATION");

    public MutationEvent(MutationType mutationType) {
        super(MUTATION);
        this.mutationType = mutationType;
    }

    public MutationType getMutationType() {
        return mutationType;
    }
}
