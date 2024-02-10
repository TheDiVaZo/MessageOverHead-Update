package me.thedivazo.messageoverhead.core.display;

import me.thedivazo.messageoverhead.core.message.Message;
import org.bukkit.entity.Player;

public interface PlayerDisplay<T extends Message<?, ?, Player>> extends Display<T>{
}
