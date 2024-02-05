package me.thedivazo.messageoverhead.core.display.impl;

import lombok.EqualsAndHashCode;
import me.thedivazo.messageoverhead.Exception.ArmorStandRenderException;
import me.thedivazo.messageoverhead.core.display.AbstractDisplay;
import me.thedivazo.messageoverhead.core.display.ArmorStandDisplay;
import me.thedivazo.messageoverhead.core.message.LocatableMessage;
import me.thedivazo.messageoverhead.exception.DisplayException;
import me.thedivazo.messageoverhead.util.armorstandline.ArmorStandLine;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@EqualsAndHashCode(callSuper = false)
public class ArmorStandDisplayImpl<E, T extends LocatableMessage<E, ?, Player>> extends AbstractDisplay<T> implements ArmorStandDisplay<E, T> {
    protected List<ArmorStandLine> lines;
    protected Function<E, String> adapter;
    protected Function<String, ArmorStandLine> armorStandFactory;
    protected double offsetX;
    protected double offsetY;
    protected double offsetZ;
    protected double widthLine;

    public ArmorStandDisplayImpl(Function<E, String> adapter, Function<String, ArmorStandLine> armorStandFactory, double offsetX, double offsetY, double offsetZ, double widthLine) {
        this.adapter = adapter;
        this.armorStandFactory = armorStandFactory;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.widthLine = widthLine;
    }

    protected void setLines() {
        this.lines = message.getText().stream().map(line->armorStandFactory.apply(adapter.apply(line))).toList();
    }

    @Override
    public void show() throws DisplayException {
        setLines();
        try {
            updateLocationForArmorStand(line-> line.render(message.getSeers()));
        } catch (ArmorStandRenderException e) {
            throw new DisplayException(e);
        }
    }

    @Override
    public void hide() {
        ListIterator<ArmorStandLine> iterator = lines.listIterator();
        while (iterator.hasNext()) {
            iterator.next().hide();
            iterator.remove();
        }
    }

    @Override
    public void updateLocation() {
        updateLocationForArmorStand(ArmorStandLine::updatePosition);
    }

    protected void updateLocationForArmorStand(Consumer<ArmorStandLine> consumer) {
        for (int i = 0; i < lines.size(); i++) {
            ArmorStandLine line = lines.get(i);
            Location location = message.getLocation();
            double biasY = (lines.size() - i) * widthLine;
            location.add(offsetX, offsetY + biasY, offsetZ);
            line.setLocation(location);
            consumer.accept(line);
        }
    }
}
