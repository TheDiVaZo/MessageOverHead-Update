package me.thedivazo.messageoverhead.message;

import org.bukkit.Location;
import org.bukkit.World;

public interface LocatableMessage extends Message {
    Location getLocation();

    interface Editable extends LocatableMessage {
        void setLocation(Location location);
        void setLocation(double x, double y, double z);
        default void setLocation(World world, double x, double y, double z) {
            setLocation(new Location(world, x, y, z));
        }
    }
}
