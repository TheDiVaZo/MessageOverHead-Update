package me.thedivazo.messageoverhead.core.dao;

import me.thedivazo.messageoverhead.core.display.Display;

import java.util.Optional;

/**
 * Хранит {@link Display}, позволяя получить их по {@link ID}
 * @param <D> Класс параметра/ов
 */
public interface Dao<T extends Display<?>, ID> {
    void saveDisplay(T display);

    Optional<T> getDisplay(ID id);

    boolean removeDisplay(T display);
    Optional<T> removeDisplayById(ID id);
    void removeAllDisplay();

    boolean containsId(ID id);
    boolean containsDisplay(T display);
}
