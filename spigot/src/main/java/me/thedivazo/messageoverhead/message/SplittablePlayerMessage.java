package me.thedivazo.messageoverhead.message;

public interface SplittablePlayerMessage<T extends PlayerMessage> extends SplittableVisiblePlayerMessage<T> {
    interface Editable<T extends PlayerMessage> extends SplittablePlayerMessage<T>, PlayerMessage.Editable, SplittableVisiblePlayerMessage.Editable<T> {}
}
