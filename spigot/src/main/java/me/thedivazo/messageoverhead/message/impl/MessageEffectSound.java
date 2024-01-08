package me.thedivazo.messageoverhead.message.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.thedivazo.messageoverhead.message.PlayerMessageEffect;
import me.thedivazo.messageoverhead.message.VisiblePlayerMessage;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

@AllArgsConstructor
@Getter
@Setter
public class MessageEffectSound implements PlayerMessageEffect<VisiblePlayerMessage> {

    protected Sound sound;
    protected int volume;
    protected int pitch;

    @Override
    public void play(VisiblePlayerMessage message) {
        message.getShowers().forEach(getPlayConsumer(message));
    }

    @Override
    public Consumer<Player> getPlayConsumer(VisiblePlayerMessage message) {
        return shower->shower.playSound(message.getLocation(), sound, volume, pitch);
    }
}
