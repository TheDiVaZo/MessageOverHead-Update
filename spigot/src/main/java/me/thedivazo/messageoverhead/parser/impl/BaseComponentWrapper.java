package me.thedivazo.messageoverhead.parser.impl;

import lombok.AllArgsConstructor;
import me.thedivazo.messageoverhead.parser.Wrapper;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author TheDiVaZo
 * created on 05.02.2024
 */
@AllArgsConstructor
public class BaseComponentWrapper implements Wrapper<BaseComponent[]> {
    protected int maxSizeLine;
    protected int wrapLengthLimit;

    protected TextComponent copyFormatting(String str, TextComponent textComponent) {
        TextComponent result = new TextComponent(textComponent);
        result.setText(str);
        return result;
    }

    protected static String substrStripEndSpaces(String str, int start, int end) {
        return StringUtils.stripEnd(str.substring(start, end), null);
    }

    protected static String substrStripStartSpaces(String str, int start, int end) {
        return StringUtils.stripStart(str.substring(start, end), null);
    }

    protected void processBaseComponent(TextComponent textComponent, List<BaseComponent[]> result, List<BaseComponent> bufferLine, AtomicInteger lineLength) {
        String str = textComponent.getText();
        if(lineLength.get() >= maxSizeLine) {
            result.add(bufferLine.toArray(BaseComponent[]::new));
            bufferLine.clear();
            lineLength.set(0);
        }

        if(lineLength.get() + str.length() <= maxSizeLine) {
            bufferLine.add(textComponent);
            lineLength.addAndGet(str.length());
        }
        else {
            TextComponent lastWord;
            TextComponent nextLine;
            int curIndex = maxSizeLine - lineLength.get();
            if (str.charAt(curIndex) == ' ') {
                lastWord = copyFormatting(
                        substrStripEndSpaces(str, 0, curIndex),
                        textComponent);
                nextLine = copyFormatting(
                        substrStripStartSpaces(str, curIndex, str.length()),
                        textComponent);
            }
            else {
                int prevSpaceIndex = str.lastIndexOf(' ',curIndex)+1;
                int nextSpaceIndex = str.indexOf(' ', curIndex);
                if (nextSpaceIndex == -1) nextSpaceIndex = str.length();

                int leftLength = curIndex - prevSpaceIndex;
                int rightLength = nextSpaceIndex - curIndex;

                if(leftLength > wrapLengthLimit || rightLength > wrapLengthLimit) {
                    lastWord = copyFormatting(
                            str.substring(0, curIndex),
                            textComponent
                    );
                    nextLine = copyFormatting(
                            str.substring(curIndex),
                            textComponent);
                }
                else {
                    boolean isWrapWithLeft = leftLength <= rightLength;
                    int wrapIndex = isWrapWithLeft ? prevSpaceIndex : nextSpaceIndex;
                    lastWord = copyFormatting(
                            substrStripEndSpaces(str, 0, wrapIndex),
                            textComponent
                    );
                    nextLine = copyFormatting(
                            substrStripStartSpaces(str, wrapIndex, str.length()),
                            textComponent);
                }
            }
            if(!lastWord.getText().isEmpty()) {
                bufferLine.add(lastWord);
                lineLength.addAndGet(lastWord.getText().length());
            }
            else {
                result.add(bufferLine.toArray(BaseComponent[]::new));
                bufferLine.clear();
                lineLength.set(0);
            }
            processBaseComponent(nextLine, result, bufferLine, lineLength);
        }
    }

    @Override
    public BaseComponent[][] apply(BaseComponent[] baseComponents) {
        List<BaseComponent[]> result = new ArrayList<>();

        List<BaseComponent> bufferLine = new ArrayList<>();
        AtomicInteger lineLength = new AtomicInteger(0);

        for (BaseComponent baseComponent : baseComponents) {
            if(!(baseComponent instanceof TextComponent textComponent)) {
                bufferLine.add(baseComponent);
                continue;
            }
            processBaseComponent(textComponent, result, bufferLine, lineLength);
        }
        if(!bufferLine.isEmpty()) result.add(bufferLine.toArray(BaseComponent[]::new));
        return result.toArray(BaseComponent[][]::new);
    }
}
