package com.aplazotest.simpleinterest.error;

/**
 * This class defines an exception to be used in case an argument or parameter
 * is invalid.
 *
 * @author cleber
 */
public class InvalidArgumentException extends Exception {

    public InvalidArgumentException(String message) {
        super(message);
    }

}
