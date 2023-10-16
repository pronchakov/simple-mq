package edu.mq.simple.storage.fs.json.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonMessage {

    @JsonProperty("headers")
    private List<JsonHeader> headers;
    @JsonProperty("type")
    private String type;
    @JsonProperty("body")
    private Object body;

}
