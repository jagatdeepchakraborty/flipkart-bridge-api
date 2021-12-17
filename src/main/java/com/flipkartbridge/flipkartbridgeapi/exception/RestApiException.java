package com.flipkartbridge.flipkartbridgeapi.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception class to capture the different types of exceptions thrown by {@code RestTemplate#exchange}
 */
public class RestApiException extends RuntimeException {
    private final Object responseBody;
    private final HttpStatus statusCode;

    public Object getResponseBody() {
        return this.responseBody;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }

    @Override
    public String toString() {
        return "RestApiException(responseBody=" + this.getResponseBody() + ", statusCode=" + this.getStatusCode() + ")";
    }

    public RestApiException(final Object responseBody, final HttpStatus statusCode) {
        this.responseBody = responseBody;
        this.statusCode = statusCode;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RestApiException)) return false;
        final RestApiException other = (RestApiException) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$responseBody = this.getResponseBody();
        final Object other$responseBody = other.getResponseBody();
        if (this$responseBody == null ? other$responseBody != null : !this$responseBody.equals(other$responseBody)) return false;
        final Object this$statusCode = this.getStatusCode();
        final Object other$statusCode = other.getStatusCode();
        if (this$statusCode == null ? other$statusCode != null : !this$statusCode.equals(other$statusCode)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RestApiException;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $responseBody = this.getResponseBody();
        result = result * PRIME + ($responseBody == null ? 43 : $responseBody.hashCode());
        final Object $statusCode = this.getStatusCode();
        result = result * PRIME + ($statusCode == null ? 43 : $statusCode.hashCode());
        return result;
    }
}
