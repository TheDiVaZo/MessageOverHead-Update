package me.thedivazo.messageoverhead.message;

public interface MessageController<T extends Message> {
    void spawnMessage(T message);
    void removeAllMessages();
    void removeMessage(T message);
}
