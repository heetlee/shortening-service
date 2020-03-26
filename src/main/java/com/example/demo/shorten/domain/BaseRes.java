package com.example.demo.shorten.domain;

import com.example.demo.shorten.common.BaseResCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class BaseRes<T,U> {
    @JsonProperty("response_code")
    Integer responseCode;
    @JsonProperty("response_message")
    String responseMessage;
    @JsonProperty("response_data")
    T responseData;
    @JsonProperty("request_data")
    U requestData;


    public BaseRes(final Integer responseCode, final String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
    private BaseRes(Integer responseCode, String responseMessage, T responseData) {
        this(responseCode, responseMessage); this.responseData = responseData;
    }
    private BaseRes(Integer responseCode, String responseMessage, T responseData, U requestData) {
        this(responseCode, responseMessage, responseData); this.requestData = requestData;
    }


    public static <T, U> BaseRes<T, U> from(BaseResCode code, String message) {
        return new BaseRes<>(code.value(), message);
    }
    private static <T, U> BaseRes<T, U> from(BaseResCode code, T responseData) {
        return new BaseRes<>(code.value(), code.reason(), responseData);
    }
    private static <U, T> BaseRes<T,U> from(BaseResCode code, T responseData, U requestData) {
        return new BaseRes<>(code.value(), code.reason(), responseData, requestData);
    }

    public static <T, U> BaseRes<T, U> success(T responseData) {
        return from(BaseResCode.OK, responseData);
    }
    public static <T, U> BaseRes<T, U> success(T responseData, U requestData) {
        return from(BaseResCode.OK, responseData, requestData);
    }
}
