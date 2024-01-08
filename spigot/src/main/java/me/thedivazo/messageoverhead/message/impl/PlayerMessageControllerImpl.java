package me.thedivazo.messageoverhead.message.impl;

import lombok.AllArgsConstructor;
import me.thedivazo.messageoverhead.message.PlayerMessage;
import me.thedivazo.messageoverhead.message.PlayerMessageController;
import me.thedivazo.messageoverhead.message.VisiblePlayerMessageDisplay;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Фасад для управления всплывающими сообщениями
 */
@AllArgsConstructor
public class PlayerMessageControllerImpl implements PlayerMessageController<PlayerMessage.Editable> {
    private double biasY;
    Consumer<Player> effectConsumer;
    protected final Map<Player,PlayerMessage.Editable> messages;

    @Override
    public void spawnMessage(PlayerMessage.Editable message) {
        Location location = message.getOwner().getLocation();
        location.setY(location.getY() + biasY);
        message.setLocation(location);
        messages.compute(message.getOwner(), (player, oldValue)->{
            if (oldValue != null) oldValue.hide();
            return message;
        });
        message.show();
        message.getShowers().forEach(effectConsumer);
    }

    @Override
    public void removeAllMessages() {
        messages.values().forEach(PlayerMessage.Editable::hide);
        messages.clear();
    }

    @Override
    public void removeMessage(PlayerMessage.Editable message) {
        if (messages.remove(message.getOwner(), message)) message.hide();
    }
}
