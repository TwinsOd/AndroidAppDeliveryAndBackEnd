package com.example.twins.nicolinska.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Twins on 14.07.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerServer {
    @JsonProperty("success")
    private String success;

    @JsonProperty("message")
    private String message;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
