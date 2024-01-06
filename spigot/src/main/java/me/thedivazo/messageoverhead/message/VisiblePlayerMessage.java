package me.thedivazo.messageoverhead.message;

import org.bukkit.entity.Player;

/**
 * Представляет собой сообщение для игрока
 */
public interface VisiblePlayerMessage extends Message.Showable<Player>, LocatableMessage {
    interface Editable extends VisiblePlayerMessage, Message.Showable.Editable<Player>, LocatableMessage.Editable {}
}
