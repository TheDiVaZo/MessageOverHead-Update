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
     * Представляет всплывающие сообщение с явным указанием зрителей (тех, кто может видеть всплывающее сообщение)
     * @param <E> Класс зрителя
     */
    interface Showable<E> extends Message {
        Collection<E> getShowers();

        interface Editable<E> extends Showable<E> {
            void setShowers(Collection<E> showers);
        }
    }

    /**
     * Представляет собой всплывающее сообщение со своей логикой отображения
     */
    interface Displayable extends Message {
        void show();
        void hide();
        boolean isShow();
    }

    /**
     * Представляет собой многострочное всплывающее сообщение.
     * @param <T> Класс всплывающих сообщений
     */
    interface Splitable<T extends Message> extends Message {
        T[] getLines();
    }
}
