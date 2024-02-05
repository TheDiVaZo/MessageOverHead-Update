package me.thedivazo.messageoverhead.core.display;

import me.thedivazo.messageoverhead.core.message.LocatableMessage;

public interface LocatableDisplay<T extends LocatableMessage<?,?, ?>> extends Display<T> {
    void updateLocation();
}
