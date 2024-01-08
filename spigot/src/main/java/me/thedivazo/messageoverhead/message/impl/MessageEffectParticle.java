package me.thedivazo.messageoverhead.message.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.thedivazo.messageoverhead.message.VisiblePlayerMessage;
import me.thedivazo.messageoverhead.message.PlayerMessageEffect;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

@AllArgsConstructor
@Getter
@Setter
public class MessageEffectParticle implements PlayerMessageEffect<VisiblePlayerMessage> {
    protected Particle particle;
    protected int count;
    protected double offsetX;
    protected double offsetY;
    protected double offsetZ;

    @Override
    public void play(VisiblePlayerMessage message) {
        message.getShowers().forEach(getPlayConsumer(message));
    }

    @Override
    public Consumer<Player> getPlayConsumer(VisiblePlayerMessage message) {
        return shower->shower.spawnParticle(particle, message.getLocation(),count, offsetX, offsetY, offsetZ);
    }
}
