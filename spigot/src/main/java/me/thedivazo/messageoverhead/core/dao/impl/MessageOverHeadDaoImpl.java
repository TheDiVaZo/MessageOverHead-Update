package me.thedivazo.messageoverhead.core.dao.impl;

import me.thedivazo.messageoverhead.core.dao.MessageOfPlayerDao;
import me.thedivazo.messageoverhead.core.display.Display;
import me.thedivazo.messageoverhead.core.display.LocatableDisplay;
import me.thedivazo.messageoverhead.core.message.LocatableMessage;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.bukkit.entity.Player;

import java.util.Optional;

public class MessageOverHeadDaoImpl<T extends LocatableDisplay<? extends LocatableMessage<?, Player, Player>>> implements MessageOfPlayerDao<T, Player> {
    protected BidiMap<Player, T> displays = new DualHashBidiMap<>();


    @Override
    public void saveDisplay(T display) {
        Player player = display.getMessage().getOwner();
        displays.compute(player, (key, value)->{
            if (value != null) value.hide();
            return display;
        });
    }

    @Override
    public Optional<T> getDisplay(Player player) {
        return Optional.ofNullable(displays.get(player));
    }

    @Override
    public boolean removeDisplay(T display) {
        return displays.removeValue(display) != null;
    }

    @Override
    public Optional<T> removeDisplayById(Player player) {
        return Optional.ofNullable(displays.remove(player));
    }

    @Override
    public void removeAllDisplay() {
        MapIterator<Player, T> mapIterator = displays.mapIterator();
        while (mapIterator.hasNext()) {
            mapIterator.next();
            mapIterator.getValue().hide();
            mapIterator.remove();
        }
    }

    @Override
    public boolean containsId(Player player) {
        return displays.containsKey(player);
    }

    @Override
    public boolean containsDisplay(T display) {
        return displays.containsValue(display);
    }
}
