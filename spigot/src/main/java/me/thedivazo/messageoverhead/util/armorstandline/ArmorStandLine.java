package me.thedivazo.messageoverhead.util.armorstandline;

import me.thedivazo.messageoverhead.Exception.ArmorStandRenderException;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface ArmorStandLine {
    void updatePosition();
    void render(Collection<Player> seers) throws ArmorStandRenderException;
    void hide();
    String getText();
    void setText(String text);
    Location getLocation();
    void setLocation(Location location);
}
