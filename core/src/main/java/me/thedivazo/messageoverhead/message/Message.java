package me.thedivazo.messageoverhead.message;

import java.util.Collection;
import java.util.List;

/**
 * Представляет собой всплывающее сообщение
 */
public interface Message {
    String getText();

    /**
     * Представляет всплывающее сообщение с явным указанием его владельца {@link T}.
     * @param <T> Класс владельца
     */
    interface Ownable<T> extends Message {
        T getOwner();
    }

    /**
     * Представляет всплывающие сообщение с явным указанием зрителей (тех, кто может видеть всплывающее сообщение) и логикой отображения
     * @param <E> Класс зрителя
     */
    interface Showable<E> extends Message {
        Collection<E> getShowers();

        void show();
        void hide();
        boolean isShow();

        interface Editable<E> extends Showable<E> {
            void setShowers(Collection<E> showers);
        }
    }

    /**
     * Представляет собой многострочное всплывающее сообщение.
     * @param <T> Класс всплывающих сообщений
     */
    interface Splitable<T extends Message> extends Message {
        T[] getLines();
    }
}
