package com.aplazotest.simpleinterest.error;

/**
 *
 * @author cleber
 */
public class RequestValidation {

    private final boolean valid;
    private final String errorMessage;

    private RequestValidation(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    public static RequestValidation valid() {
        return new RequestValidation(true, "");
    }

    public static RequestValidation invalid(String message) {
        return new RequestValidation(false, message);
    }

    public boolean isValid() {
        return valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
