package com.project.kudawala.reponses;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("message")
    String message;

    @SerializedName("httpStatus")
    Integer httpStatus;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }
}
