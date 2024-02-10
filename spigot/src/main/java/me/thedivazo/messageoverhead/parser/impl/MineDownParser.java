package me.thedivazo.messageoverhead.parser.impl;

import de.themoep.minedown.MineDown;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.thedivazo.messageoverhead.parser.Parser;
import net.md_5.bungee.api.chat.BaseComponent;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MineDownParser implements Parser<BaseComponent[]> {
    @Getter
    private static final MineDownParser instance = new MineDownParser();

    @Override
    public BaseComponent[] apply(String s) {
        return new MineDown(s).toComponent();
    }
}
