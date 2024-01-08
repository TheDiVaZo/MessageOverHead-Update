package me.thedivazo.messageoverhead.message;

import org.bukkit.entity.Player;

public interface PlayerMessageEffect<T extends VisiblePlayerMessage> extends MessageEffect.Consumable<T, Player> {
}
