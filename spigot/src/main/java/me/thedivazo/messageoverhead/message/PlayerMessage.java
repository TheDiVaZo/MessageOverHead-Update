package me.thedivazo.messageoverhead.message;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * Представляет собой сообщение игрока
 */
public interface PlayerMessage extends VisiblePlayerMessage, Message.Ownable<Player> {
    interface Editable extends PlayerMessage, VisiblePlayerMessage.Editable {

    }
}
