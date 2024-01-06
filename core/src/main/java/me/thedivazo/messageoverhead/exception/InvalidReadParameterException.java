package me.thedivazo.messageoverhead.exception;

public class InvalidReadParameterException extends Exception {
    public InvalidReadParameterException() {
        this(new Object[0]);
        DisplayMessage ds = null;
    }

    public InvalidReadParameterException(Object... placeholders) {
        super(String.format("A parameter"+ (placeholders.length > 0 ? " '{0}'" : "") +" was passed that does not exist", placeholders));
    }
}
