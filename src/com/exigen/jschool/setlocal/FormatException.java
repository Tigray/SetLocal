package com.exigen.jschool.setlocal;

/**
 * This exception is thrown if file format is wrong
 */
public class FormatException extends Exception {
    /**
     * Exception constructor.
     * @param msg Error message
     */
    public FormatException(String msg) {
        super(msg);
    }

}
