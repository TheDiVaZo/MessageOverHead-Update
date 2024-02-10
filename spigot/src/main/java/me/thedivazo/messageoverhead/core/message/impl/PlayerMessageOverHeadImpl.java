package me.thedivazo.messageoverhead.core.message.impl;

import me.thedivazo.messageoverhead.core.message.DefaultMessageImpl;
import me.thedivazo.messageoverhead.core.message.EntityLinkLocationMessage;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;

public class PlayerMessageOverHeadImpl<E> extends DefaultMessageImpl<E, Player, Player> implements EntityLinkLocationMessage<E, Player, Player> {
    @Override
    public void setSeers(Collection<Player> collection) {
        super.setSeers(Set.copyOf(collection));
    }
}
