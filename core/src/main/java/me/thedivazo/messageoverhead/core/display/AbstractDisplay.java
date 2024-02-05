package me.thedivazo.messageoverhead.core.display;

import me.thedivazo.messageoverhead.core.message.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractDisplay<T extends Message<?, ?, ?>> implements Display<T> {
    protected T message;

    @Override
    public T getMessage() {
        return message;
    }

    @Override
    public void setMessage(T message) {
        this.message = message;
    }
}
