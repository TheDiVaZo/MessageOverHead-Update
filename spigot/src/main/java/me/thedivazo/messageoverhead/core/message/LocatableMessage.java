package me.thedivazo.messageoverhead.core.message;

import org.bukkit.Location;

public interface LocatableMessage<E, O, S> extends Message<E, O, S> {
    Location getLocation();
    void setLocation(Location location) throws UnsupportedOperationException;
}
