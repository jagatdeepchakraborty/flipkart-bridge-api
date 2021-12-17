package com.flipkartbridge.flipkartbridgeapi.exception;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

public class StatusCode {
    @NonNull
    private final HttpStatus httpStatus;
    @NonNull
    private final String description;
    @NonNull
    private final String code;
    public static final StatusCode SUCCESS = StatusCode.newBuilder(HttpStatus.OK).setDescription("Request was processed successfully.").setCode(0).build();
    public static final StatusCode NO_SHIPMENTS_FOUND = StatusCode.newBuilder(HttpStatus.NOT_FOUND).setDescription("No Record(s) Found.").setCode(-1).build();
    public static final StatusCode FAILURE = StatusCode.newBuilder(HttpStatus.BAD_REQUEST).setDescription("Failed to call service").setCode(1).build();

    public static StatusCode.Builder newBuilder(@NonNull HttpStatus httpStatus) {
        if (httpStatus == null) {
            throw new NullPointerException("httpStatus is marked non-null but is null");
        }
        return new Builder(httpStatus);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }


    public static final class Builder {
        private final HttpStatus httpStatus;
        private String description;
        private String code;

        private Builder(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            this.description = httpStatus.getReasonPhrase();
            this.code = Integer.toString(httpStatus.value());
        }

        public Builder setDescription(@NonNull String description) {
            if (description == null) {
                throw new NullPointerException("description is marked non-null but is null");
            }
            this.description = description;
            return this;
        }

        public Builder setCode(@NonNull String code) {
            if (code == null) {
                throw new NullPointerException("code is marked non-null but is null");
            }
            this.code = code;
            return this;
        }

        public Builder setCode(int code) {
            return setCode(Integer.toString(code));
        }

        public StatusCode build() {
            return new StatusCode(httpStatus, description, code);
        }
    }

    @Override
    public String toString() {
        return "StatusCode(httpStatus=" + this.getHttpStatus() + ", description=" + this.getDescription() + ", code=" + this.getCode() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof StatusCode)) return false;
        final StatusCode other = (StatusCode) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$httpStatus = this.getHttpStatus();
        final Object other$httpStatus = other.getHttpStatus();
        if (this$httpStatus == null ? other$httpStatus != null : !this$httpStatus.equals(other$httpStatus)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) return false;
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof StatusCode;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $httpStatus = this.getHttpStatus();
        result = result * PRIME + ($httpStatus == null ? 43 : $httpStatus.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $code = this.getCode();
        result = result * PRIME + ($code == null ? 43 : $code.hashCode());
        return result;
    }

    private StatusCode(@NonNull final HttpStatus httpStatus, @NonNull final String description, @NonNull final String code) {
        if (httpStatus == null) {
            throw new NullPointerException("httpStatus is marked non-null but is null");
        }
        if (description == null) {
            throw new NullPointerException("description is marked non-null but is null");
        }
        if (code == null) {
            throw new NullPointerException("code is marked non-null but is null");
        }
        this.httpStatus = httpStatus;
        this.description = description;
        this.code = code;
    }
}
