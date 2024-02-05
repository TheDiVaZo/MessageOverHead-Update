package me.thedivazo.messageoverhead.parser;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.function.Function;

/**
 * Парсит текст в объект определенного типа
 * @param <T> Класс объекта
 */
public interface Parser<T> extends Function<String, T> {
}
