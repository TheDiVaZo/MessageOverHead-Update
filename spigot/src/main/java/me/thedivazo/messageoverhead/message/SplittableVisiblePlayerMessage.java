package me.thedivazo.messageoverhead.message;

public interface SplittableVisiblePlayerMessage<T extends VisiblePlayerMessage> extends VisiblePlayerMessage, Message.Splitable<T> {
    interface Editable<T extends VisiblePlayerMessage> extends SplittableVisiblePlayerMessage<T>, VisiblePlayerMessage.Editable {}
}
