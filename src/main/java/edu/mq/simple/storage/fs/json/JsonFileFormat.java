package edu.mq.simple.storage.fs.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.storage.fs.FileFormat;
import edu.mq.simple.storage.fs.json.entity.JsonMessage;
import lombok.SneakyThrows;

public class JsonFileFormat implements FileFormat {

    private final ObjectMapper mapper = new ObjectMapper();
    private final BodyConverter bodyConverter = new BodyConverter();

    public JsonFileFormat() {
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    @Override
    public SimpleMQMessage transformTextToMessage(String text) {
        try {
            final var jsonMessage = mapper.readValue(text, JsonMessage.class);
            final var message = bodyConverter.convertBody(jsonMessage);
            return message;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public String transformMessageToText(SimpleMQMessage message) {

        final var body = bodyConverter.convertBody(message);

        final var builder = JsonMessage.builder();
        builder.type(message.getType().name().toLowerCase());
        builder.body(body);
        // TODO: add headers from SimpleMQMessage
        final var jsonMessage = builder.build();

        final var s = mapper.writeValueAsString(jsonMessage);
        return s;
    }
}
