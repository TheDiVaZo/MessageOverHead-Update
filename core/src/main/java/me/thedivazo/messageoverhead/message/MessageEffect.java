package me.thedivazo.messageoverhead.message;

import java.util.function.Consumer;

/**
 * Отвечает за дополнительные визуальные эффекты для всплывающего сообщения
 */
public interface MessageEffect<T extends Message> {
    void play(T message);

    interface Consumable<T extends Message, V> extends MessageEffect<T> {
        Consumer<V> getPlayConsumer(T message);
    }
}
