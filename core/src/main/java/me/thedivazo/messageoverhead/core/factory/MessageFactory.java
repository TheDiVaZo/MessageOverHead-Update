package me.thedivazo.messageoverhead.core.factory;

import me.thedivazo.messageoverhead.core.message.Message;

public interface MessageFactory<T extends Message<?, ?, ?>, P> {
    T createMessage(P params);
}
