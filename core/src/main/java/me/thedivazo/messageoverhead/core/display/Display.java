package me.thedivazo.messageoverhead.core.display;

import me.thedivazo.messageoverhead.core.message.Message;
import me.thedivazo.messageoverhead.exception.DisplayException;

import java.util.Collection;

/**
 * Представляет собой логику отображения сообщения.
 * @param <T> {@link Message <T>}
 */
public interface Display<T extends Message<?, ?, ?>> {
    T getMessage();
    void setMessage(T message);
    void show() throws DisplayException;
    void hide();

}
