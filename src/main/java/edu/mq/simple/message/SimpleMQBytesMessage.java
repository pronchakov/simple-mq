package edu.mq.simple.message;

import jakarta.jms.BytesMessage;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;

import java.util.Enumeration;

public class SimpleMQBytesMessage implements BytesMessage {
    @Override
    public long getBodyLength() throws JMSException {
        return 0;
    }

    @Override
    public boolean readBoolean() throws JMSException {
        return false;
    }

    @Override
    public byte readByte() throws JMSException {
        return 0;
    }

    @Override
    public int readUnsignedByte() throws JMSException {
        return 0;
    }

    @Override
    public short readShort() throws JMSException {
        return 0;
    }

    @Override
    public int readUnsignedShort() throws JMSException {
        return 0;
    }

    @Override
    public char readChar() throws JMSException {
        return 0;
    }

    @Override
    public int readInt() throws JMSException {
        return 0;
    }

    @Override
    public long readLong() throws JMSException {
        return 0;
    }

    @Override
    public float readFloat() throws JMSException {
        return 0;
    }

    @Override
    public double readDouble() throws JMSException {
        return 0;
    }

    @Override
    public String readUTF() throws JMSException {
        return null;
    }

    @Override
    public int readBytes(byte[] value) throws JMSException {
        return 0;
    }

    @Override
    public int readBytes(byte[] value, int length) throws JMSException {
        return 0;
    }

    @Override
    public void writeBoolean(boolean value) throws JMSException {

    }

    @Override
    public void writeByte(byte value) throws JMSException {

    }

    @Override
    public void writeShort(short value) throws JMSException {

    }

    @Override
    public void writeChar(char value) throws JMSException {

    }

    @Override
    public void writeInt(int value) throws JMSException {

    }

    @Override
    public void writeLong(long value) throws JMSException {

    }

    @Override
    public void writeFloat(float value) throws JMSException {

    }

    @Override
    public void writeDouble(double value) throws JMSException {

    }

    @Override
    public void writeUTF(String value) throws JMSException {

    }

    @Override
    public void writeBytes(byte[] value) throws JMSException {

    }

    @Override
    public void writeBytes(byte[] value, int offset, int length) throws JMSException {

    }

    @Override
    public void writeObject(Object value) throws JMSException {

    }

    @Override
    public void reset() throws JMSException {

    }

    @Override
    public String getJMSMessageID() throws JMSException {
        return null;
    }

    @Override
    public void setJMSMessageID(String id) throws JMSException {

    }

    @Override
    public long getJMSTimestamp() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSTimestamp(long timestamp) throws JMSException {

    }

    @Override
    public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
        return new byte[0];
    }

    @Override
    public void setJMSCorrelationIDAsBytes(byte[] correlationID) throws JMSException {

    }

    @Override
    public void setJMSCorrelationID(String correlationID) throws JMSException {

    }

    @Override
    public String getJMSCorrelationID() throws JMSException {
        return null;
    }

    @Override
    public Destination getJMSReplyTo() throws JMSException {
        return null;
    }

    @Override
    public void setJMSReplyTo(Destination replyTo) throws JMSException {

    }

    @Override
    public Destination getJMSDestination() throws JMSException {
        return null;
    }

    @Override
    public void setJMSDestination(Destination destination) throws JMSException {

    }

    @Override
    public int getJMSDeliveryMode() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSDeliveryMode(int deliveryMode) throws JMSException {

    }

    @Override
    public boolean getJMSRedelivered() throws JMSException {
        return false;
    }

    @Override
    public void setJMSRedelivered(boolean redelivered) throws JMSException {

    }

    @Override
    public String getJMSType() throws JMSException {
        return null;
    }

    @Override
    public void setJMSType(String type) throws JMSException {

    }

    @Override
    public long getJMSExpiration() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSExpiration(long expiration) throws JMSException {

    }

    @Override
    public long getJMSDeliveryTime() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSDeliveryTime(long deliveryTime) throws JMSException {

    }

    @Override
    public int getJMSPriority() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSPriority(int priority) throws JMSException {

    }

    @Override
    public void clearProperties() throws JMSException {

    }

    @Override
    public boolean propertyExists(String name) throws JMSException {
        return false;
    }

    @Override
    public boolean getBooleanProperty(String name) throws JMSException {
        return false;
    }

    @Override
    public byte getByteProperty(String name) throws JMSException {
        return 0;
    }

    @Override
    public short getShortProperty(String name) throws JMSException {
        return 0;
    }

    @Override
    public int getIntProperty(String name) throws JMSException {
        return 0;
    }

    @Override
    public long getLongProperty(String name) throws JMSException {
        return 0;
    }

    @Override
    public float getFloatProperty(String name) throws JMSException {
        return 0;
    }

    @Override
    public double getDoubleProperty(String name) throws JMSException {
        return 0;
    }

    @Override
    public String getStringProperty(String name) throws JMSException {
        return null;
    }

    @Override
    public Object getObjectProperty(String name) throws JMSException {
        return null;
    }

    @Override
    public Enumeration getPropertyNames() throws JMSException {
        return null;
    }

    @Override
    public void setBooleanProperty(String name, boolean value) throws JMSException {

    }

    @Override
    public void setByteProperty(String name, byte value) throws JMSException {

    }

    @Override
    public void setShortProperty(String name, short value) throws JMSException {

    }

    @Override
    public void setIntProperty(String name, int value) throws JMSException {

    }

    @Override
    public void setLongProperty(String name, long value) throws JMSException {

    }

    @Override
    public void setFloatProperty(String name, float value) throws JMSException {

    }

    @Override
    public void setDoubleProperty(String name, double value) throws JMSException {

    }

    @Override
    public void setStringProperty(String name, String value) throws JMSException {

    }

    @Override
    public void setObjectProperty(String name, Object value) throws JMSException {

    }

    @Override
    public void acknowledge() throws JMSException {

    }

    @Override
    public void clearBody() throws JMSException {

    }

    @Override
    public <T> T getBody(Class<T> c) throws JMSException {
        return null;
    }

    @Override
    public boolean isBodyAssignableTo(Class c) throws JMSException {
        return false;
    }
}
