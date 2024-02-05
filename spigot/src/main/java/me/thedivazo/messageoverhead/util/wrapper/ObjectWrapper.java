package me.thedivazo.messageoverhead.util.wrapper;

/**
 * Разделяет объект на массив таких же объектов.
 * @param <T> Класс объекта
 */
public interface ObjectWrapper<T> {
    T[] wrap(T object);
}
