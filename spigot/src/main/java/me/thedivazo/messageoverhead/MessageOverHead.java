package me.thedivazo.messageoverhead;

import lombok.Getter;
import me.thedivazo.messageoverhead.core.dao.Dao;
import me.thedivazo.messageoverhead.core.dao.MessageOfPlayerDao;
import me.thedivazo.messageoverhead.core.dao.impl.MessageOverHeadDaoImpl;
import me.thedivazo.messageoverhead.core.display.LocatableDisplay;
import me.thedivazo.messageoverhead.core.message.LocatableMessage;
import me.thedivazo.messageoverhead.core.message.Message;
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
    private Dao<? extends LocatableDisplay<? extends LocatableMessage<?, Player, Player>>, Player> mohDao = new MessageOverHeadDaoImpl<>();

}
