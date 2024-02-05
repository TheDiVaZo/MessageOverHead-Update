package me.thedivazo.messageoverhead.core.display;

import me.thedivazo.messageoverhead.core.message.LocatableMessage;
import org.bukkit.entity.Player;

public interface LocatablePlayerSeersDisplay<T extends LocatableMessage<?, ?, Player>> extends LocatableDisplay<T> {
}
