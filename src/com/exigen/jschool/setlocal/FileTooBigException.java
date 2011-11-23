package com.exigen.jschool.setlocal;

import java.io.IOException;

/**
 * This exception is thrown if file is too big for our program.
 */
public class FileTooBigException extends IOException {

     /**
     * Exception constructor.
     * @param msg Error message
     */
    public FileTooBigException(String msg) {
        super(msg);
    }
}
