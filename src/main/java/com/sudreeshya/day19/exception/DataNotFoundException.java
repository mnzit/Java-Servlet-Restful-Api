package com.sudreeshya.day19.exception;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String message) {
        super(message);
    }
}
