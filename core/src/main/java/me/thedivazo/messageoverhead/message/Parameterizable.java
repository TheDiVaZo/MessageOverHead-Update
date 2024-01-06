package me.thedivazo.messageoverhead.message;

import me.thedivazo.messageoverhead.exception.InvalidReadParameterException;
import me.thedivazo.messageoverhead.exception.InvalidReadParameterTypeException;

import java.util.Map;

/**
 * Представляет собой объект, который можно параметризовать
 */
interface Parameterizable {
    /**
     * Метод позволяющий изменить параметры.
     * @param params Карта, представляющая параметры, где key - название параметра, а value - его значение
     * @throws InvalidReadParameterTypeException Выбрасывается, если параметр существует, но его значение указано не верно
     * @throws InvalidReadParameterException Выбрасывается, если параметра не существует
     */
    void readParams(Map<String, Object> params) throws InvalidReadParameterTypeException, InvalidReadParameterException;
}
