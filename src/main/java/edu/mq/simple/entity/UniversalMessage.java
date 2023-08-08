package edu.mq.simple.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class UniversalMessage {

    @JsonProperty("headers")
    @JsonInclude
    private Map<String, Object> headers;
    @JsonProperty("body")
    @JsonInclude
    private String body;

}
