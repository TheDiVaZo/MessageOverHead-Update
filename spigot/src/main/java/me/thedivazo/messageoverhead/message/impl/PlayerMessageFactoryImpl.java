package me.thedivazo.messageoverhead.message.impl;

import de.themoep.minedown.MineDown;
import lombok.AllArgsConstructor;
import me.thedivazo.messageoverhead.message.PlayerMessage;
import me.thedivazo.messageoverhead.message.PlayerMessageFactory;
import me.thedivazo.messageoverhead.message.VisiblePlayerMessage;
import me.thedivazo.messageoverhead.util.ChatComponentUtils;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

@AllArgsConstructor
public class  PlayerMessageFactoryImpl implements PlayerMessageFactory {
    protected Function<Optional<Player>,Collection<Player>> showersFunction;
    protected int sizeLine;

    private ArmorStandMessage[] splitText(String text, Collection<Player> showers) {
        BaseComponent[][] lines = ChatComponentUtils.split(new MineDown(text).toComponent(), sizeLine);
        return Arrays.stream(lines)
                .map(baseComponents ->  new ArmorStandMessage(BaseComponent.toLegacyText(baseComponents), showers, null))
                .toArray(value -> new ArmorStandMessage[0]);
    }
    @Override
    public VisiblePlayerMessage createMessage(String text) {
        Collection<Player> showers = showersFunction.apply(Optional.empty());
        VisiblePlayerMessage.Editable[] messages = splitText(text, showers);
        return new VisiblePlayerMessagesImpl(messages, showers, null);
    }

    @Override
    public PlayerMessage createMessage(Player owner, String text) {
        Collection<Player> showers = showersFunction.apply(Optional.of(owner));
        VisiblePlayerMessage.Editable[] messages = splitText(text, showers);
        return new PlayerMessageImpl(messages, showers, null, owner);
    }
}
