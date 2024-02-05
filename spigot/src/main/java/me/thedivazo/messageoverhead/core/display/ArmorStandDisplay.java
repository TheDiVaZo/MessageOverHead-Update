package me.thedivazo.messageoverhead.core.display;

import me.thedivazo.messageoverhead.core.message.LocatableMessage;
import org.bukkit.entity.Player;

public interface ArmorStandDisplay<E, T extends LocatableMessage<E, ?, Player>> extends LocatableDisplay<T> {
}
