package edu.mq.simple.storage.fs.json.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.jms.message.type.map.SimpleMQMapMessage;
import edu.mq.simple.jms.message.type.map.SimpleMQMapMessageElement;
import edu.mq.simple.storage.fs.json.BodyTypeConverter;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

public class MapMessageBodyTypeConverter implements BodyTypeConverter {
    @SneakyThrows
    @Override
    public SimpleMQMessage convert(String message) {
        final var mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        final var result = new SimpleMQMapMessage();

        final Map<String, Map<String, Object>> data = mapper.readValue(message, Map.class);

        for (Map.Entry<String, Map<String, Object>> entry : data.entrySet()) {
            final var key = entry.getKey();
            final var entryValue = entry.getValue();
            final var dataType = String.valueOf(entryValue.get("type"));
            var dataValue = entryValue.get("value");

            dataValue = switch (dataType) {
                case "boolean" -> dataValue;
                case "character" -> String.valueOf(dataValue).charAt(0);
                case "integer" -> dataValue;
                case "long" -> Long.valueOf(String.valueOf(dataValue));
                case "short" -> Short.valueOf(String.valueOf(dataValue));
                case "double" -> Double.valueOf(String.valueOf(dataValue));
                case "float" -> Float.valueOf(String.valueOf(dataValue));
                case "byte" -> Base64.getDecoder().decode(String.valueOf(dataValue))[0];
                case "bytes" -> Base64.getDecoder().decode(String.valueOf(dataValue));
                default -> dataValue;
            };

            if (dataType.matches("object\\[.*\\]")) {
                final var classType = dataType.substring(dataType.indexOf('[') + 1, dataType.indexOf(']'));
                try {
                    final Class<?> aClass = Class.forName(classType);
                    final Object instance = aClass.getDeclaredConstructor().newInstance();

                    final var dataStructure = (Map<String, Object>) dataValue;
                    for (Map.Entry<String, Object> structureEntry : dataStructure.entrySet()) {
                        final var k = structureEntry.getKey();
                        final var v = structureEntry.getValue();

                        PropertyUtils.setSimpleProperty(instance, k, v);
                    }
                    dataValue = instance;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            result.getMap().put(key, dataValue);
        }
        return result;
    }

    @SneakyThrows
    @Override
    public Object convert(SimpleMQMessage message) {
        final var mapMessage = (SimpleMQMapMessage) message;
        TreeMap<String, SimpleMQMapMessageElement> result = new TreeMap<>();
        for (Map.Entry<String, Object> entry : mapMessage.getMap().entrySet()) {
            var value = entry.getValue();
            var type = value.getClass().getSimpleName().toLowerCase();

            type = switch (type) {
                case "byte[]" -> "bytes";
                case "boolean", "byte", "character", "double", "float", "integer", "long", "short", "string" -> type;
                default -> "object[" + value.getClass().getCanonicalName() + "]";
            };

            value = switch (type) {
                case "byte" -> Base64.getEncoder().encodeToString(new byte[]{(byte) value});
                default -> value;
            };

            final var mapElement = new SimpleMQMapMessageElement(type, value);
            result.put(entry.getKey(), mapElement);
        }
        return result;
    }
}
