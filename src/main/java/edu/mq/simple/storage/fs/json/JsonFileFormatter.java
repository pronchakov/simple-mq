package edu.mq.simple.storage.fs.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.storage.fs.FileFormatter;
import lombok.SneakyThrows;

public class JsonFileFormatter implements FileFormatter {

    private final ObjectMapper mapper = new ObjectMapper();
    private final BodyConverter bodyConverter = new BodyConverter();

    public JsonFileFormatter() {
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    @SneakyThrows
    @Override
    public SimpleMQMessage toMessage(String text) {
        try {

            final var jsonNode = mapper.readTree(text);

            final var bodyNode = jsonNode.get("body");
            final var body = switch (bodyNode.getNodeType()) {
                case OBJECT -> bodyNode.toPrettyString();
                default -> bodyNode.asText();
            };

            final var type = jsonNode.get("type").textValue();
            final var message = bodyConverter.toMessage(type, body);

            final var headers = jsonNode.get("headers");
            for (JsonNode header : headers) {
                final var name = header.get("name").textValue();
                final var hType = header.get("type").textValue();
                var value = header.get("value").textValue();
                switch (hType) {
                    // todo: add data types
                    case "string": {
                        message.setStringProperty(name, value);
                    }
                };

            }

            return message;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public String toText(SimpleMQMessage message) {
        final var body = bodyConverter.toObject(message);

        final var objectNode = mapper.createObjectNode();
        final var headersNode = mapper.createArrayNode();
        message.getHeaders().forEach((name, simpleHeader) -> {
            final var headerNode = mapper.createObjectNode();
            headerNode.put("name", simpleHeader.getName());
            headerNode.put("type", simpleHeader.getType());
            headerNode.put("value", simpleHeader.getValue().toString()); // todo: nah
            headersNode.add(headerNode);
        });
        objectNode.put("headers", headersNode);
        objectNode.put("type", message.getType().toString().toLowerCase());
        objectNode.put("body", mapper.convertValue(body, JsonNode.class));

        final var s = mapper.writeValueAsString(objectNode);
        return s;
    }
}
