package com.sudreeshya.day19.exception;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
public class SaveUpdateFailureException extends RuntimeException{

    public SaveUpdateFailureException(String message) {
        super(message);
    }
}
