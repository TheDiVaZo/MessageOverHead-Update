package me.thedivazo.messageoverhead.util;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.function.Function;

public class Adapters {
    public static final Function<TextComponent[], String> TEXT_COMPONENT_TO_STRING_ADAPTER = TextComponent::toLegacyText;
}
