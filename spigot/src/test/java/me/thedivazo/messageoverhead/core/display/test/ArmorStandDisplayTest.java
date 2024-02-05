package me.thedivazo.messageoverhead.core.display.test;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import me.thedivazo.messageoverhead.core.display.ArmorStandDisplay;
import me.thedivazo.messageoverhead.core.display.ArmorStandDisplayMock;
import me.thedivazo.messageoverhead.core.display.impl.ArmorStandDisplayImpl;
import me.thedivazo.messageoverhead.core.message.LocatableMessage;
import me.thedivazo.messageoverhead.exception.DisplayException;
import me.thedivazo.messageoverhead.util.armorstandline.ArmorStandLine;
import me.thedivazo.messageoverhead.util.armorstandline.ArmorStandLineMock;
import me.thedivazo.messageoverhead.util.armorstandline.MessageUtils;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class ArmorStandDisplayTest {
    private Function<String, ArmorStandLine> armorStandLineFactory;
    private Function<String, String> adapter;
    private ServerMock server;
    private MessageUtils messageUtils;

    @BeforeEach
    public void setUp() {
        armorStandLineFactory = (line) -> (ArmorStandLine) new ArmorStandLineMock(line);
        adapter = (text)->text;
        server = MockBukkit.mock();
        messageUtils = new MessageUtils(server);
    }

    @AfterEach
    public void tearDown()
    {
        MockBukkit.unmock();
    }

    @Test
    public void show() throws DisplayException {
        LocatableMessage<String, Player, Player> locatableMessage = messageUtils.generateMessage();
        ArmorStandDisplayMock<String, LocatableMessage<String, Player, Player>> armorStandDisplay = new ArmorStandDisplayMock<>(adapter, armorStandLineFactory, 0, 0, 0, 1);

        //test
        armorStandDisplay.setMessage(locatableMessage);
        armorStandDisplay.show();

        //assert
        assertEquals(locatableMessage.getText(), armorStandDisplay.getArmorStandLines().stream().map(ArmorStandLine::getText).toList());
        armorStandDisplay.getArmorStandLines().forEach(line->assertTrue(((ArmorStandLineMock) line).isRender()));
        armorStandDisplay.getArmorStandLines().forEach(line->assertNotNull(((ArmorStandLineMock) line).getRenderedLocation()));
    }

    @Test
    public void hide() throws DisplayException {
        LocatableMessage<String, Player, Player> locatableMessage = messageUtils.generateMessage();
        ArmorStandDisplayMock<String, LocatableMessage<String, Player, Player>> armorStandDisplay = new ArmorStandDisplayMock<>(adapter, armorStandLineFactory, 0, 0, 0, 1);

        //test
        armorStandDisplay.setMessage(locatableMessage);
        armorStandDisplay.show();
        armorStandDisplay.hide();

        //assert
        assertTrue(armorStandDisplay.getArmorStandLines().isEmpty());
    }
}
