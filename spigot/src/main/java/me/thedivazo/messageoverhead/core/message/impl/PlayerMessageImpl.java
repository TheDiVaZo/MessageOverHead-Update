package me.thedivazo.messageoverhead.core.message.impl;

import me.thedivazo.messageoverhead.core.message.DefaultMessageImpl;
import me.thedivazo.messageoverhead.core.message.EntityMessage;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;

public class PlayerMessageImpl<E> extends DefaultMessageImpl<E, Player, Player> implements EntityMessage<E, Player, Player> {
    @Override
    public void setSeers(Collection<Player> collection) {
        super.setSeers(Set.copyOf(collection));
    }
}
