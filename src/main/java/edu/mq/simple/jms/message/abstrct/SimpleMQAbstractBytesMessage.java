package edu.mq.simple.jms.message.abstrct;

import jakarta.jms.BytesMessage;
import jakarta.jms.JMSException;

public abstract class SimpleMQAbstractBytesMessage extends SimpleMQAbstractMessage implements BytesMessage {
    @Override
    public long getBodyLength() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean readBoolean() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public byte readByte() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int readUnsignedByte() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public short readShort() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int readUnsignedShort() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public char readChar() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int readInt() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public long readLong() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public float readFloat() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public double readDouble() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String readUTF() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int readBytes(byte[] value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int readBytes(byte[] value, int length) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeBoolean(boolean value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeByte(byte value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeShort(short value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeChar(char value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeInt(int value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeLong(long value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeFloat(float value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeDouble(double value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeUTF(String value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeBytes(byte[] value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeBytes(byte[] value, int offset, int length) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void writeObject(Object value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void reset() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

}
