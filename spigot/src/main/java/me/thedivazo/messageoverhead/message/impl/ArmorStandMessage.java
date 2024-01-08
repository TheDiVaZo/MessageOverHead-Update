package me.thedivazo.messageoverhead.message.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataValue;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.thedivazo.messageoverhead.MessageOverHead;
import me.thedivazo.messageoverhead.message.*;
import me.thedivazo.messageoverhead.util.VersionWrapper;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Реализует отображение сообщений через {@link org.bukkit.entity.EntityType#ARMOR_STAND}
 */
public class ArmorStandMessage implements VisiblePlayerMessage.Editable {

    private final boolean isSmall = true;
    private final boolean noBasePlate = true;
    private final boolean isMarker = true;
    private final boolean invisible = true;

    private final ProtocolManager pm = ProtocolLibrary.getProtocolManager();

    private static final VersionWrapper MC_VERSION = MessageOverHead.MINECRAFT_VERSION;
    private static final VersionWrapper MC_1_20 = VersionWrapper.valueOf("1.20");
    private static final VersionWrapper MC_1_19 = VersionWrapper.valueOf("1.19");
    private static final VersionWrapper MC_1_18 = VersionWrapper.valueOf("1.18");
    private static final VersionWrapper MC_1_17 = VersionWrapper.valueOf("1.17");
    private static final VersionWrapper MC_1_16 = VersionWrapper.valueOf("1.16");
    private static final VersionWrapper MC_1_15 = VersionWrapper.valueOf("1.15");
    private static final VersionWrapper MC_1_14 = VersionWrapper.valueOf("1.14");
    private static final VersionWrapper MC_1_13 = VersionWrapper.valueOf("1.13");
    private static final VersionWrapper MC_1_12 = VersionWrapper.valueOf("1.12");

    private static final int CUSTOM_NAME_INDEX = 2;
    private static final int CUSTOM_NAME_VISIBLE_INDEX = 3;
    private static final int PARAM_ARMOR_STAND_INDEX;

    static {
        if (MC_VERSION.equalsMinor(MC_1_17) || MC_VERSION.greaterMinor(MC_1_17)) {
            PARAM_ARMOR_STAND_INDEX = 15;
        } else if (MC_VERSION.greaterMinor(MC_1_14)) {
            PARAM_ARMOR_STAND_INDEX = 14;
        } else if (MC_VERSION.equalsMinor(MC_1_14)) {
            PARAM_ARMOR_STAND_INDEX = 13;
        } else {
            PARAM_ARMOR_STAND_INDEX = 11;
        }
    }

    private final WrappedDataWatcher.Serializer serBoolean = WrappedDataWatcher.Registry.get(Boolean.class);
    private final WrappedDataWatcher.Serializer serByte = WrappedDataWatcher.Registry.get(Byte.class);

    private final WrappedDataWatcher metadata = new WrappedDataWatcher();
    private final int id = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
    private final UUID uuid = UUID.randomUUID();

    private final String text;
    private Collection<Player> showers;
    private Location location;

    private boolean isShow = false;

    public ArmorStandMessage(String text, Collection<Player> showers, Location location) {
        this.text = text;
        this.showers = showers;
        this.location = location;
    }

    private void setMetadata() {
        Optional<?> opt;
        if (MC_VERSION.lessMinor(MC_1_13) || MC_VERSION.equalsMinor(MC_1_13)) {
            opt = Optional.of(WrappedChatComponent.fromText(text).getHandle());
        } else {
            opt = Optional.of(WrappedChatComponent.fromChatMessage(text)[0].getHandle());
        }
        if(MC_VERSION.greaterMinor(MC_1_14) || MC_VERSION.equalsMinor(MC_1_14)) {
            WrappedDataWatcher.Serializer serChatComponent = WrappedDataWatcher.Registry.getChatComponentSerializer(true);
            metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(CUSTOM_NAME_INDEX, serChatComponent), opt);
        } else {
            WrappedDataWatcher.Serializer serString = WrappedDataWatcher.Registry.get(String.class);
            metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(CUSTOM_NAME_INDEX, serString), text);
        }
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(CUSTOM_NAME_VISIBLE_INDEX, serBoolean), true);
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(PARAM_ARMOR_STAND_INDEX, serByte), (byte)
                ((isMarker ? 0x10 : 0) | (isSmall ? 0x01 : 0) | (noBasePlate ? 0x08 : 0))
        );
        metadata.setObject(0, serByte, (byte) (invisible ? 0x20 : 0));
    }

    private PacketContainer getFakeStandPacket() {
        PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
        packetContainer.getModifier().writeDefaults();
        packetContainer.getIntegers().write(0, id);

        if(MC_VERSION.lessMinor(MC_1_13) || MC_VERSION.equalsMinor(MC_1_13)) {
            packetContainer.getIntegers().write(6, 78);
        } else {
            packetContainer.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);
        }
        // Set location
        packetContainer.getDoubles().write(0, location.getX());
        packetContainer.getDoubles().write(1, location.getY());
        packetContainer.getDoubles().write(2, location.getZ());
        packetContainer.getUUIDs().write(0, uuid);
        return packetContainer;
    }

    private PacketContainer getMetaPacket() {
        PacketContainer metaPacket = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        metaPacket.getIntegers().write(0, id);
        try {
            final List<WrappedDataValue> wrappedDataValueList = getWrappedDataValues();

            metaPacket.getDataValueCollectionModifier().write(0, wrappedDataValueList);
        }
        catch (Exception e) {
            metaPacket.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        }
        return metaPacket;
    }

    @NotNull
    private List<WrappedDataValue> getWrappedDataValues() {
        final List<WrappedDataValue> wrappedDataValueList = new ArrayList<>();
        for (final WrappedWatchableObject entry : metadata.getWatchableObjects()) {
            if (entry == null) continue;
            final WrappedDataWatcher.WrappedDataWatcherObject watcherObject = entry.getWatcherObject();
            WrappedDataValue wrappedDataValue = new WrappedDataValue(
                    watcherObject.getIndex(),
                    watcherObject.getSerializer(),
                    entry.getRawValue()
            );
            wrappedDataValueList.add(wrappedDataValue);
        }
        return wrappedDataValueList;
    }

    private PacketContainer getTeleportPositionPacket() {
        PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.ENTITY_TELEPORT);
        packetContainer.getModifier().writeDefaults();
        packetContainer.getIntegers().write(0, id);
        packetContainer.getDoubles().write(0, location.getX());
        packetContainer.getDoubles().write(1, location.getY());
        packetContainer.getDoubles().write(2, location.getZ());
        packetContainer.getBooleans().write(0, false);
        return packetContainer;
    }

    private void updatePosition() {
        PacketContainer newPositionPacket = getTeleportPositionPacket();
        showers.forEach(player -> pm.sendServerPacket(player, newPositionPacket));
    }

    @Override
    public void show() {
        if(isShow) hide();
        setMetadata();
        PacketContainer metaPacket = getMetaPacket();
        PacketContainer fakeStandPacket = getFakeStandPacket();

         for (Player player : showers) {
             pm.sendServerPacket(player, fakeStandPacket);
             pm.sendServerPacket(player, metaPacket);
         }
         isShow = true;
    }

    @Override
    public void hide() {
        PacketContainer removeStandPacket = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
        if(MC_VERSION.greaterMinor(MC_1_18) || MC_VERSION.equalsMinor(MC_1_18)) {
            List<Integer> entity = new ArrayList<>();
            entity.add(id);
            removeStandPacket.getIntLists().write(0, entity);
        } else if(MC_VERSION.equalsMinor(MC_1_17)) {
            try {
                removeStandPacket.getModifier().write(0, new IntArrayList(new int[]{id}));
            } catch (Throwable e) {
                removeStandPacket.getModifier().write(0, id);
            }
        } else {
            removeStandPacket.getModifier().write(0, new int[]{id});
        }
        showers.forEach(player-> pm.sendServerPacket(player ,removeStandPacket));
        isShow = false;
    }

    @Override
    public boolean isShow() {
        return isShow;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Collection<Player> getShowers() {
        return Collections.unmodifiableCollection(showers);
    }

    @Override
    public Location getLocation() {
        return location.clone();
    }

    @Override
    public void setLocation(Location location) {
        this.location = location.clone();
        if (!isShow) updatePosition();
    }

    @Override
    public void setLocation(double x, double y, double z) {
        if (location == null) throw new IllegalStateException("It is impossible to get the world because location is null");
        setLocation(new Location(location.getWorld(), x, y, z));
    }

    @Override
    public void setShowers(Collection<Player> showers) {
        if(isShow) show();
        this.showers = new ArrayList<>(showers);
    }
}
