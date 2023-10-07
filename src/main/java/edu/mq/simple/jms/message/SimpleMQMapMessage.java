package edu.mq.simple.jms.message;

import edu.mq.simple.jms.message.abstrct.SimpleMQAbstractMapMessage;
import jakarta.jms.JMSException;
import lombok.*;

import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

public class SimpleMQMapMessage extends SimpleMQAbstractMapMessage {

    private Map<String, Object> map = new TreeMap<>();

    @Override
    public SimpleMQJMSMessageType getType() {
        return SimpleMQJMSMessageType.MAP;
    }

    @SneakyThrows
    @Override
    public Object getData() {
        TreeMap<String, MapElement> result = new TreeMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
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

            final var mapElement = new MapElement(type, value);
            result.put(entry.getKey(), mapElement);
        }
        return result;
    }

    @Override
    public void setBoolean(String name, boolean value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setByte(String name, byte value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setShort(String name, short value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setChar(String name, char value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setInt(String name, int value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setLong(String name, long value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setFloat(String name, float value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setDouble(String name, double value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setString(String name, String value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setBytes(String name, byte[] value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setBytes(String name, byte[] value, int offset, int length) throws JMSException {
        final var result = new byte[length];
        System.arraycopy(value, offset, result, 0, length);
        map.put(name, result);
    }

    @Override
    public void setObject(String name, Object value) throws JMSException {
        map.put(name, value);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private class MapElement<T> {
        private String type;
        private T value;
    }
}
