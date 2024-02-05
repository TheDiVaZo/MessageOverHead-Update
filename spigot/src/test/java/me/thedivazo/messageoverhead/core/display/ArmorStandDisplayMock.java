package me.thedivazo.messageoverhead.core.display;

import me.thedivazo.messageoverhead.core.display.impl.ArmorStandDisplayImpl;
import me.thedivazo.messageoverhead.core.message.LocatableMessage;
import me.thedivazo.messageoverhead.util.armorstandline.ArmorStandLine;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Function;

public class ArmorStandDisplayMock<E, T extends LocatableMessage<E, ?, Player>> extends ArmorStandDisplayImpl<E, T> {
    public ArmorStandDisplayMock(Function<E, String> adapter, Function<String, ArmorStandLine> armorStandFactory, double offsetX, double offsetY, double offsetZ, double widthLine) {
        super(adapter, armorStandFactory, offsetX, offsetY, offsetZ, widthLine);
    }

    public List<ArmorStandLine> getArmorStandLines() {
        return lines;
    }
}
