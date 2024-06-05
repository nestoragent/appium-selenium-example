package com.xm.lib.pageFactory;

/**
 * Created by VelichkoAA on 14.01.2016.
 */
public class PageInitializationException extends Exception {
    /**
     * <p>Constructor for PageInitializationException.</p>
     *
     * @param message a {@link String} object.
     * @param e a {@link Throwable} object.
     */
    public PageInitializationException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * <p>Constructor for PageInitializationException.</p>
     *
     * @param message a {@link String} object.
     */
    public PageInitializationException(String message) {
        super(message);
    }

}
