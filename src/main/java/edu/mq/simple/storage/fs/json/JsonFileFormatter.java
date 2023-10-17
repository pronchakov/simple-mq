package edu.mq.simple.storage.fs.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.storage.fs.FileFormatter;
import edu.mq.simple.storage.fs.json.entity.JsonMessage;
import lombok.SneakyThrows;

public class JsonFileFormatter implements FileFormatter {

    private final ObjectMapper mapper = new ObjectMapper();
    private final BodyConverter bodyConverter = new BodyConverter();

    public JsonFileFormatter() {
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    @Override
    public SimpleMQMessage transform(String text) {
        try {

            final var jsonNode = mapper.readTree(text);

            final var bodyNode = jsonNode.get("body");
            final var body = switch (bodyNode.getNodeType()) {
                case OBJECT -> bodyNode.toPrettyString();
                default -> bodyNode.asText();
            };

            final var jsonMessage = JsonMessage.builder()
//                    .headers()
                    .type(jsonNode.get("type").textValue())
                    .body(body)
                    .build();
            final var message = bodyConverter.convertBody(jsonMessage);
            return message;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public String transform(SimpleMQMessage message) {

        final var body = bodyConverter.convertBody(message);

        final var objectNode = mapper.createObjectNode();
        objectNode.put("headers", mapper.nullNode());
        objectNode.put("type", message.getType().toString().toLowerCase());
        objectNode.put("body", mapper.convertValue(body, JsonNode.class));

//        final var builder = JsonMessage.builder();
//        builder.type(message.getType().name().toLowerCase());
//        builder.body(body);
//        // TODO: add headers from SimpleMQMessage
//        final var jsonMessage = builder.build();

        final var s = mapper.writeValueAsString(objectNode);
        return s;
    }
}
