package edu.mq.simple.message;

import lombok.Getter;

@Getter
public class UnknownTypeException extends Throwable {

    private String type;

    public UnknownTypeException(String type) {
        this.type = type;
    }
}
