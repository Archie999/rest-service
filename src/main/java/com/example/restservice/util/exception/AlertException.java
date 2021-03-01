package com.example.restservice.util.exception;


/**
 * Created by Archie on 2021-03-01.
 */
public class AlertException extends RuntimeException {

    private static final long serialVersionUID = -994962710559017255L;

    private int code;

    public AlertException(String message) {
        super(message);
        this.code = AlertCodeConstant.NORMAL;
    }

    /**
     *
     * @param code 异常码
     * @param message
     */
    public AlertException(int code, String message) {
        super(message);
        this.code = code;
    }
}
