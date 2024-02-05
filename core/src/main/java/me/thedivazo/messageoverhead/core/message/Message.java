package me.thedivazo.messageoverhead.core.message;

import java.util.Collection;
import java.util.List;

/**
 * Представляет собой модель, хранящая информацию о сообщении
 * @param <E> Тип данных, которое содержит всплывающее сообщение
 * @param <O> Класс сущности, являющееся владельцем сообщения
 * @param <S> Класс сущности, которому будет отображаться сообщение
 */
public interface Message<E, O, S> {
    List<E> getText();
    void setText(List<E> text) throws UnsupportedOperationException;

    O getOwner();

    Collection<S> getSeers();
    void setSeers(Collection<S> seers) throws UnsupportedOperationException;

    void setOwner(O owner) throws UnsupportedOperationException;
}
