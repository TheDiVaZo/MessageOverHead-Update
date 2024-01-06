package me.thedivazo.messageoverhead.message;

import net.md_5.bungee.api.chat.BaseComponent;

/**
 * Всплывающее сообщение поддерживающее компоненты чата из BungeeCord API
 */
public interface ChatComponentMessage extends Message {
    BaseComponent[] getChatComponents();
}
