package me.thedivazo.messageoverhead.message.impl;

import lombok.AllArgsConstructor;
import me.thedivazo.messageoverhead.message.Message;
import me.thedivazo.messageoverhead.message.PlayerMessage;
import me.thedivazo.messageoverhead.message.VisiblePlayerMessage;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collection;

public class PlayerMessageImpl extends VisiblePlayerMessagesImpl implements PlayerMessage {
    protected Player owner;

    public PlayerMessageImpl(VisiblePlayerMessage.Editable[] messages, Collection<Player> showers, Location location, Player owner) {
        super(messages, showers, location);
        this.owner = owner;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }
}
