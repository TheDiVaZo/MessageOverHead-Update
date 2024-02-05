package me.thedivazo.messageoverhead.util.armorstandline;

import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMockFactory;
import me.thedivazo.messageoverhead.core.message.LocatableMessage;
import me.thedivazo.messageoverhead.core.message.impl.DefaultLocatableMessageImpl;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageUtils {
    protected ServerMock serverMock;
    protected PlayerMockFactory playerMockFactory;
    protected WorldMock worldMock;
    protected Random random = new Random();

    public MessageUtils(ServerMock serverMock) {
        this.serverMock = serverMock;
        this.playerMockFactory = new PlayerMockFactory(serverMock);
        this.worldMock = serverMock.addSimpleWorld("test");
    }

    public LocatableMessage<String, Player, Player> generateMessage() {
        LocatableMessage<String, Player, Player> message = new DefaultLocatableMessageImpl<>();

        message.setText(List.of("This is test string.", "I like monkey.", "And i like sphere!"));

        PlayerMock owner = playerMockFactory.createRandomPlayer();
        serverMock.addPlayer(owner);

        List<Player> seers = new ArrayList<>();
        for (int i = 0; i < random.nextInt(1,10); i++) {
            PlayerMock seer = playerMockFactory.createRandomPlayer();
            serverMock.addPlayer(seer);
            seers.add(seer.getPlayer());
        }

        message.setOwner(owner.getPlayer());
        message.setSeers(seers);

        Location location = new Location(getRandomLocation());
        message.setLocation(location);

        return message;
    }

    public Location getRandomLocation() {
        return new Location(serverMock.getWorld("test"), random.nextDouble(), random.nextDouble(), random.nextDouble());
    }
}
