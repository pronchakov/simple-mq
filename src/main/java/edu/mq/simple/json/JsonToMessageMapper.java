package edu.mq.simple.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mq.simple.entity.UniversalMessage;

import java.io.IOException;

public class JsonToMessageMapper {
    private final ObjectMapper mapper = new ObjectMapper();

    public UniversalMessage transformMessage(byte[] message) {
        try {
            return mapper.readValue(message, UniversalMessage.class);
        } catch (IOException e) {
            throw new RuntimeException(e); // TODO:
        }
    }
}
