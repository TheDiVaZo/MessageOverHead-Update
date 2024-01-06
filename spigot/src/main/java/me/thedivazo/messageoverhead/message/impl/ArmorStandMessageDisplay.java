package me.thedivazo.messageoverhead.message.impl;

import me.thedivazo.messageoverhead.message.MessageDisplay;

public class ArmorStandMessageDisplay implements MessageDisplay<ArmorStandMessage> {
    @Override
    public void show(ArmorStandMessage message) {
        message.show();
    }

    @Override
    public void hide(ArmorStandMessage message) {
        message.hide();
    }
}
