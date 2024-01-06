package me.thedivazo.messageoverhead.message;

/**
 *
 * Представляет собой фабрику, создающую всплывающие сообщения.
 * @param <T> Тип класса, который создает фабрика
 */
public interface MessageFactory<T extends Message> {
    /**
     * Создать всплывающее сообщение
     * @param text текст всплывающего сообщения
     * @return объект всплывающего сообщения
     */
    T createMessage(String text);

    /**
     * Представляет собой фабрику, создающую всплывающие сообщения у которых есть владелец (см. {@link Message.Ownable <V>})
     * @param <V> Класс привязываемого объекта
     * @param <T> Класс всплывающего сообщения, наследованного от {@link Message.Ownable<V>}
     */
    interface Ownable<V, T extends Message.Ownable<V>, S extends Message> extends MessageFactory<S> {
        T createMessage(V owner, String text);
    }
}
