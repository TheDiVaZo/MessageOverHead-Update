package me.thedivazo.messageoverhead.message.impl;

import lombok.AllArgsConstructor;
import me.thedivazo.messageoverhead.message.Message;
import me.thedivazo.messageoverhead.message.VisiblePlayerMessage;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@AllArgsConstructor
public class VisiblePlayerMessagesImpl implements VisiblePlayerMessage.Editable, Message.Splitable<VisiblePlayerMessage.Editable> {

    protected Editable[] messages;
    protected Collection<Player> showers;
    protected Location location;


    @Override
    public String getText() {
        return Arrays.stream(messages).map(Editable::getText).collect(Collectors.joining(" "));
    }

    @Override
    public Collection<Player> getShowers() {
        return Collections.unmodifiableCollection(showers);
    }

    @Override
    public void show() {
        for (Editable message : messages) {
            message.show();
        }
    }

    @Override
    public void hide() {
        for (Editable message : messages) {
            message.hide();
        }
    }

    @Override
    public boolean isShow() {
        return false;
    }

    @Override
    public void setShowers(Collection<Player> showers) {
        this.showers = showers;
        for (Editable message : this.messages) {
            message.setShowers(showers);
        }
    }

    @Override
    public Editable[] getLines() {
        return messages;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
        for (Editable message : messages) {
            message.setLocation(location);
        }
    }

    @Override
    public void setLocation(double x, double y, double z) {
        setLocation(new Location(location.getWorld(), x, y, z));
    }
}
