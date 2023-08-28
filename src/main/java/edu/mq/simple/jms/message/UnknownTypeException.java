package edu.mq.simple.jms.message;

import lombok.Getter;

@Getter
public class UnknownTypeException extends Throwable {

    private String type;

    public UnknownTypeException(String type) {
        this.type = type;
    }
}
