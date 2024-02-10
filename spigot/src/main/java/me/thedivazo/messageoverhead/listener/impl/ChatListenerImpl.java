package me.thedivazo.messageoverhead.listener.impl;

import me.thedivazo.messageoverhead.listener.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatListenerImpl implements ChatListener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent e) {

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerPrivateChat(PlayerCommandPreprocessEvent e) {
        String cmd = e.getMessage().trim();
        String[] args = cmd.split(" ");
        if(args.length < 3) return;
        if(!args[0].equals("/msg") && !args[0].equals("/tell")) return;
        Player targetPlayer = Bukkit.getPlayer(args[1]);
        if(targetPlayer==null) return;
        Player player = e.getPlayer();
        args[0] = "";
        args[1] = "";
        String message = String.join(" ", args);


    }
}
