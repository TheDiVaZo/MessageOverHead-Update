package me.thedivazo.messageoverhead.util.armorstandline;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.thedivazo.messageoverhead.Exception.ArmorStandRenderException;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@Data
public class ArmorStandLineMock implements ArmorStandLine {

    protected Collection<Player> seers;
    protected Location location;
    protected String text;

    //Поля для проверки
    @Setter(AccessLevel.PRIVATE)
    @Getter
    protected Location renderedLocation;
    @Setter(AccessLevel.PRIVATE)
    @Getter
    protected boolean isRender = false;

    public ArmorStandLineMock(String text) {
        this.text = text;
    }

    @Override
    public void updatePosition() {
        renderedLocation = location;
    }

    @Override
    public void hide() {
        isRender = false;
    }

    @Override
    public void render(Collection<Player> seers) throws ArmorStandRenderException {
        this.seers = seers;
        isRender = true;
        renderedLocation = location;
    }
}