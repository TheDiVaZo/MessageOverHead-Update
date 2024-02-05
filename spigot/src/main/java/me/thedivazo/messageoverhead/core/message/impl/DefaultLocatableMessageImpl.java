package me.thedivazo.messageoverhead.core.message.impl;

import me.thedivazo.messageoverhead.core.message.DefaultMessageImpl;
import me.thedivazo.messageoverhead.core.message.LocatableMessage;
import org.bukkit.Location;

public class DefaultLocatableMessageImpl<E, O, S> extends DefaultMessageImpl<E, O, S> implements LocatableMessage<E, O, S> {
    protected Location location;

    @Override
    public Location getLocation() {
        return location.clone();
    }

    @Override
    public void setLocation(Location location) {
        this.location = location.clone();
    }
}
