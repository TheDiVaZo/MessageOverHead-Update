package me.thedivazo.messageoverhead;

import lombok.Getter;
import me.thedivazo.messageoverhead.message.Message;
import me.thedivazo.messageoverhead.message.MessageFactory;
import me.thedivazo.messageoverhead.message.MessageVisibility;
import me.thedivazo.messageoverhead.util.VersionWrapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

//@Plugin(name = MessageOverHead.NAME, version = "4.0.0")
//@Dependency(value = "ProtocolLib")
//@SoftDependsOn(value = {
//        @SoftDependency(value = "PlaceholderAPI"),
//        @SoftDependency(value = "SuperVanish"),
//        @SoftDependency(value = "PremiumVanish"),
//        @SoftDependency(value = "ChatControllerRed"),
//        @SoftDependency(value = "Essentials"),
//        @SoftDependency(value = "CMI")
//})
//@Author(value = "TheDiVaZo")
//@ApiVersion(value = ApiVersion.Target.v1_13)
public class MessageOverHead extends JavaPlugin {
    public static final VersionWrapper MINECRAFT_VERSION = VersionWrapper.valueOf(Bukkit.getVersion());
    public static final String NAME = "MessageOverHead";

    @Getter
    private static MessageFactory.Ownable<Player, PlayerMessage, Message> messageFactory;

    private static MessageVisibility<PlayerMessage, Player> messageVisibility;

    public static void spawnMessage(Player owner, String text) {
        PlayerMessage message = messageFactory.createMessage(owner, text);
        messageVisibility.show(message, owner);
    }
}
