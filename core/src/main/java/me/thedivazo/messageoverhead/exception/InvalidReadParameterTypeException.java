package me.thedivazo.messageoverhead.exception;

public class InvalidReadParameterTypeException extends Exception {
    public InvalidReadParameterTypeException() {
        this(new Object[0]);
    }

    public InvalidReadParameterTypeException(Object... placeholders) {
        super(String.format("The parameter"+ (placeholders.length > 0 ? " '{0}'" : "") +" has an incorrect value"+ (placeholders.length > 1 ? " '{1}'" : ""), placeholders));
    }
}
