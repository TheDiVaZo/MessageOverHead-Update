package me.thedivazo.messageoverhead.message.impl;

import lombok.AllArgsConstructor;
import me.thedivazo.messageoverhead.message.SplittableVisiblePlayerMessage;
import me.thedivazo.messageoverhead.message.VisiblePlayerMessage;
import me.thedivazo.messageoverhead.message.VisiblePlayerMessageDisplay;
import org.bukkit.Location;

@AllArgsConstructor
public class VisiblePlayerMultiMessageDisplay implements VisiblePlayerMessageDisplay<SplittableVisiblePlayerMessage.Editable<VisiblePlayerMessage.Editable>> {

    private double sizeBetween;

    @Override
    public void show(SplittableVisiblePlayerMessage.Editable<VisiblePlayerMessage.Editable> message) {
        for (int i = 0; i < message.getLines().length; i++) {
            VisiblePlayerMessage.Editable line = message.getLines()[i];
            Location lineLocation = message.getLocation().clone();
            lineLocation.setY(lineLocation.getY() + (message.getLines().length - i) * sizeBetween);
            line.setLocation(lineLocation);
            line.show();
        }

    }

    @Override
    public void hide(SplittableVisiblePlayerMessage.Editable<VisiblePlayerMessage.Editable> message) {
        for (VisiblePlayerMessage.Editable line : message.getLines()) {
            line.hide();
        }
    }
}
