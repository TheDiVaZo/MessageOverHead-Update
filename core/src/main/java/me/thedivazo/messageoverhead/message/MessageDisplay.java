package me.thedivazo.messageoverhead.message;

import java.util.Collection;
import java.util.Collections;

/**
 * Отвечает за логику отображения сообщения
 * @param <T> Поддерживаемый тип всплывающего сообщения
 */
public interface MessageDisplay<T extends Message> {
    void show(T message);
    void hide(T message);
}
