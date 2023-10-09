package edu.mq.simple.jms.message;

import edu.mq.simple.jms.message.abstrct.SimpleMQAbstractMapMessage;
import jakarta.jms.JMSException;
import lombok.*;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

public class SimpleMQMapMessage extends SimpleMQAbstractMapMessage {

    private Map<String, Object> map = new TreeMap<>();

    public SimpleMQMapMessage() {
    }

    public SimpleMQMapMessage(Map<String, Map<String, Object>> data) {
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

            map.put(key, dataValue);
        }
    }

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

    @Override
    public boolean getBoolean(String name) throws JMSException {
        return (Boolean) map.get(name);
    }

    @Override
    public byte getByte(String name) throws JMSException {
        return (Byte) map.get(name);
    }

    @Override
    public short getShort(String name) throws JMSException {
        return (Short) map.get(name);
    }

    @Override
    public char getChar(String name) throws JMSException {
        return (Character) map.get(name);
    }

    @Override
    public int getInt(String name) throws JMSException {
        return (Integer) map.get(name);
    }

    @Override
    public long getLong(String name) throws JMSException {
        return (Long) map.get(name);
    }

    @Override
    public float getFloat(String name) throws JMSException {
        return (Float) map.get(name);
    }

    @Override
    public double getDouble(String name) throws JMSException {
        return (Double) map.get(name);
    }

    @Override
    public String getString(String name) throws JMSException {
        return (String) map.get(name);
    }

    @Override
    public byte[] getBytes(String name) throws JMSException {
        return (byte[]) map.get(name);
    }

    @Override
    public Object getObject(String name) throws JMSException {
        return map.get(name);
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
