package me.thedivazo.messageoverhead.core.dao;

import me.thedivazo.messageoverhead.core.display.Display;
import me.thedivazo.messageoverhead.core.display.LocatableDisplay;
import me.thedivazo.messageoverhead.core.message.LocatableMessage;
import me.thedivazo.messageoverhead.core.message.Message;
import org.bukkit.entity.Player;

public interface MessageOfPlayerDao<T extends Display<? extends Message<?, Player, Player>>, ID> extends Dao<T, ID> {
}
