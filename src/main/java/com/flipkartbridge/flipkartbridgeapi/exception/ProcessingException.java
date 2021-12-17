package com.flipkartbridge.flipkartbridgeapi.exception;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

/**
 * General runtime, application processing exception
 */
public class ProcessingException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final StatusCode statusCode;

    private ProcessingException(StatusCode statusCode, HttpStatus httpStatus, Throwable cause, String message) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
    }

    /**
     * Creates a new {@link Builder} instance using the given required {@link StatusCode}.
     *
     * @param statusCode the required {@link StatusCode} to build with
     * @return a new {@link Builder} instance
     */
    public static Builder newBuilder(@NonNull StatusCode statusCode) {
        if (statusCode == null) {
            throw new NullPointerException("statusCode is marked non-null but is null");
        }
        return new Builder(statusCode);
    }

    public Builder toBuilder() {
        return newBuilder(statusCode).setHttpStatus(httpStatus).setMessage(getMessage()).setCause(getCause());
    }


    /**
     * Builder class for {@link ProcessingException}
     */
    public static final class Builder {
        private final StatusCode statusCode;
        private Throwable cause;
        private HttpStatus httpStatus;
        private String message;

        private Builder(StatusCode statusCode) {
            this.statusCode = statusCode;
            this.httpStatus = statusCode.getHttpStatus();
            this.message = statusCode.getDescription();
        }

        /**
         * @param httpStatus the new HTTP status
         * @return {@code this}
         * @see #getHttpStatus()
         */
        public Builder setHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        /**
         * @param cause the new cause
         * @return {@code this}
         * @see #getCause()
         */
        public Builder setCause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        /**
         * @param message the new message
         * @return {@code this}
         * @see #getMessage()
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * @return a new {@link ProcessingException} from this builder
         */
        public ProcessingException build() {
            return new ProcessingException(statusCode, httpStatus, cause, message);
        }
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }
}
