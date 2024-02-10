package me.thedivazo.messageoverhead.parser.test;

import me.thedivazo.messageoverhead.parser.impl.BaseComponentWrapper;
import me.thedivazo.messageoverhead.parser.impl.MineDownParser;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.junit.jupiter.api.Test;

class BaseComponentWrapperTest {
    @Test
    public void test() {
        BaseComponentWrapper wrapper = new BaseComponentWrapper(15, 4);
        String testStr = "&cF1 &eS23 &cF4 &eS5 &cF678 &eS9 &cF1011 &eS12 &cF131415 &eS16 &cF17 &eS18 &cF19 &eS &cF &eSw";
        BaseComponent[][] result = wrapper.apply(MineDownParser.getInstance().apply(testStr));

        for (BaseComponent[] baseComponents : result) {
            for (BaseComponent baseComponent : baseComponents) {
                if (baseComponent instanceof TextComponent textComponent) System.out.print(textComponent.getText()); ;
            }
            System.out.println("-");
        }

        System.out.println(result);
    }
}