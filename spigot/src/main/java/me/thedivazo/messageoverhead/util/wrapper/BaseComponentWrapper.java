package me.thedivazo.messageoverhead.util.wrapper;

import lombok.AllArgsConstructor;
import net.md_5.bungee.api.chat.BaseComponent;

/**
 * Разделяет сообщение (в виде {@link BaseComponent}) на массив строк определенной длины
 */
@AllArgsConstructor
public class BaseComponentWrapper implements ObjectWrapper<BaseComponent[]> {

    protected int lineLength;

    @Override
    public BaseComponent[][] wrap(BaseComponent[] message) {
        return new BaseComponent[0][];
    }
}
