package me.thedivazo.messageoverhead.message.impl;

import me.thedivazo.messageoverhead.message.PlayerMessage;
import me.thedivazo.messageoverhead.message.VisiblePlayerMessage;
import me.thedivazo.messageoverhead.message.PlayerMessageController;
import me.thedivazo.messageoverhead.message.VisiblePlayerMessageDisplay;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Фасад для управления всплывающими сообщениями
 */
public class PlayerMessageControllerImpl implements PlayerMessageController {

    Consumer<Player> effectConsumer;
    VisiblePlayerMessageDisplay visiblePlayerMessageDisplay;
    protected Map<Player, PlayerMessage.Editable> messages = new HashMap<>();

    @Override
    public void spawnMessage(PlayerMessage.Editable message) {
        Player owner = message.getOwner();
        message.setLocation(owner.getLocation());
        messages.compute(owner, (player, oldValue)->{
            if (oldValue != null) visiblePlayerMessageDisplay.show(oldValue);
            return message;
        });
        visiblePlayerMessageDisplay.show(message);
        message.getShowers().forEach(effectConsumer);
    }

    @Override
    public void removeAllMessages() {
        messages.values().forEach(visiblePlayerMessageDisplay::hide);
        messages.clear();
    }

    @Override
    public void removeMessage(PlayerMessage.Editable message) {
        if (messages.remove(message.getOwner(), message))  visiblePlayerMessageDisplay.hide(message);
    }
}
