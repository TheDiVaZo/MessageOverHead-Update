package me.thedivazo.messageoverhead.message.impl;

import de.themoep.minedown.MineDown;
import me.thedivazo.messageoverhead.message.PlayerMessage;
import me.thedivazo.messageoverhead.message.PlayerMessageFactory;
import me.thedivazo.messageoverhead.message.VisiblePlayerMessage;
import me.thedivazo.messageoverhead.util.ChatComponentUtils;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class PlayerMessageFactoryImpl implements PlayerMessageFactory {
    protected Supplier<Collection<Player>> supplier;
    protected int sizeLine;
    protected double sizeBetweenLines;

    private VisiblePlayerMessage.Editable[] splitText(String text, Collection<Player> showers) {
        BaseComponent[][] lines = ChatComponentUtils.split(new MineDown(text).toComponent(), sizeLine);
        return Arrays.stream(lines)
                .map(baseComponents ->  new ArmorStandMessage(BaseComponent.toLegacyText(baseComponents), showers, null))
                .toArray(value -> new VisiblePlayerMessage.Editable[0]);
    }
    @Override
    public VisiblePlayerMessage createMessage(String text) {
        Collection<Player> showers = supplier.get();
        VisiblePlayerMessage.Editable[] messages = splitText(text, showers);
        return new VisiblePlayerMessagesImpl(messages, showers, null, sizeBetweenLines);
    }

    @Override
    public PlayerMessage createMessage(Player owner, String text) {
        Collection<Player> showers = supplier.get();
        VisiblePlayerMessage.Editable[] messages = splitText(text, showers);
        return new PlayerMessageImpl(messages, showers, null, sizeBetweenLines, owner);
    }
}
