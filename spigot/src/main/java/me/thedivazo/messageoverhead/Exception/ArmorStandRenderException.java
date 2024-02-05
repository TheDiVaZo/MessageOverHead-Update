package me.thedivazo.messageoverhead.Exception;

public class ArmorStandRenderException extends RuntimeException {
    public ArmorStandRenderException() {
        super();
    }

    public ArmorStandRenderException(String message) {
        super(message);
    }

    public ArmorStandRenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArmorStandRenderException(Throwable cause) {
        super(cause);
    }

    protected ArmorStandRenderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
