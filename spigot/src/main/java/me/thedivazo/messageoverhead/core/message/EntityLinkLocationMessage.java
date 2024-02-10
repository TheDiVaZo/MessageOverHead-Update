package me.thedivazo.messageoverhead.core.message;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public interface EntityLinkLocationMessage<E, O extends Entity, S> extends LocatableMessage<E, O, S>{
    @Override
    default Location getLocation() {
        return getOwner().getLocation();
    }

    @Override
    default void setLocation(Location location) {
        throw new UnsupportedOperationException("This message cannot have a location, since it is tied to the owner of the message");
    }
}
