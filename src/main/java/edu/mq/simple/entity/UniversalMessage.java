package edu.mq.simple.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class UniversalMessage {

    @JsonProperty("headers")
    @JsonInclude
    private Map<String, Object> headers;

    @JsonProperty("bodyType")
    @JsonInclude
    private String bodyType;
    @JsonProperty("body")
    @JsonInclude
    private String body;

}
