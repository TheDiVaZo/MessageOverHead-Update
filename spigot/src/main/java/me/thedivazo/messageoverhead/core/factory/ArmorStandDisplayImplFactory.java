package me.thedivazo.messageoverhead.core.factory;

import lombok.AllArgsConstructor;
import me.thedivazo.messageoverhead.core.display.ArmorStandDisplay;
import me.thedivazo.messageoverhead.core.display.impl.ArmorStandDisplayImpl;
import me.thedivazo.messageoverhead.util.armorstandline.ArmorStandLine;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class ArmorStandDisplayImplFactory<E> implements DisplayFactory<ArmorStandDisplayImpl<E, ?>>{
    protected Function<E, String> adapter;
    protected Function<String, ArmorStandLine> armorStandFactory;
    protected double offsetX;
    protected double offsetY;
    protected double offsetZ;
    protected double widthLine;

    @Override
    public ArmorStandDisplayImpl<E, ?> createDisplay() {
        return new ArmorStandDisplayImpl<>(
                adapter,
                armorStandFactory,
                offsetX,
                offsetY,
                offsetZ,
                widthLine
        );
    }
}
