package me.thedivazo.messageoverhead.parser;

import lombok.AllArgsConstructor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author TheDiVaZo
 * created on 05.02.2024
 */
@AllArgsConstructor
public class BaseComponentWrapParserImpl implements BaseComponentWrapParser {
    protected BaseComponentParser baseComponentParser;
    protected int maxSizeLine;
    protected int maxDistanceToWordFromEnd;

    @Override
    public BaseComponent[][] apply(String str) {
        BaseComponent[] baseComponentText = baseComponentParser.apply(str);
        List<BaseComponent[]> result = new ArrayList<>();
        List<BaseComponent> baseComponentLine = new ArrayList<>();

        int currentLength = 0;

        for (BaseComponent baseComponent : baseComponentText) {
            if (!(baseComponent instanceof TextComponent textComponent)) {
                baseComponentLine.add(baseComponent);
                continue;
            }

            String text = textComponent.getText();
            if (currentLength + text.length() <= maxSizeLine) {
                currentLength += text.length();
                baseComponentLine.add(baseComponent);
                continue;
            }

            String[] words = text.split(" ");
            int prevWordLength = 0;
            for (String word : words) {
                prevWordLength += Math.max(1,word.length());
                if (currentLength + prevWordLength > maxSizeLine && !word.isEmpty()) {
                    String endPrevLineWord = word.substring(0, maxSizeLine - currentLength);
                    String startNextLineWord = word.substring(maxSizeLine - currentLength);

                    TextComponent textComponentEndPrevLineWord = new TextComponent(endPrevLineWord);
                    textComponent.copyFormatting(textComponentEndPrevLineWord);

                    baseComponentLine.add(textComponentEndPrevLineWord);

                    int countLines = startNextLineWord.length() / maxSizeLine;
                    for (int i = 0; i < countLines; i++) {
                        result.add(baseComponentLine.toArray(BaseComponent[]::new));
                        baseComponentLine = new ArrayList<>(1);

                        int startIndex = i*maxSizeLine;
                        int endIndex = Math.min((i+1)*maxSizeLine, startNextLineWord.length());

                        TextComponent textComponentLine = new TextComponent(
                                startNextLineWord.substring(startIndex, endIndex));
                        baseComponentLine.add(textComponentLine);
                        currentLength = endIndex;
                        prevWordLength = 0;
                    }

                }
                else if (!word.isEmpty()){
                    TextComponent textComponentWord = new TextComponent(word);
                    textComponent.copyFormatting(textComponentWord);
                    baseComponentLine.add(textComponentWord);
                }
                prevWordLength+=1;
            }
            if (!baseComponentLine.isEmpty()) result.add(baseComponentLine.toArray(BaseComponent[]::new));
        }
        return result.toArray(BaseComponent[][]::new);
    }
}
