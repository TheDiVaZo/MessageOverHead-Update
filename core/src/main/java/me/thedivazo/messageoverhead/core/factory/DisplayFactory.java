package me.thedivazo.messageoverhead.core.factory;

import me.thedivazo.messageoverhead.core.display.Display;

public interface DisplayFactory<T extends Display<?>> {
    T createDisplay();
}
