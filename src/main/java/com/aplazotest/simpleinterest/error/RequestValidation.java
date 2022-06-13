package com.aplazotest.simpleinterest.error;

/**
 * This class defines the validation result for a request.
 *
 * @author cleber
 */
public class RequestValidation {

    /**
     * Wether the request is valid or not.
     */
    private final boolean valid;
    /**
     * Error message in case the request is invalid.
     */
    private final String errorMessage;

    private RequestValidation(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    /**
     * Creates a valid result for a request validation.
     *
     * @return The request validation.
     */
    public static RequestValidation valid() {
        return new RequestValidation(true, "");
    }

    /**
     * Creates an invalid result for a request validation.
     *
     * @param message The message justifying why the request is invalid.
     * @return The request validation.
     */
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
