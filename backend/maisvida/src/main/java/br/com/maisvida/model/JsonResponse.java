package br.com.maisvida.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Jorginho on 30/03/2017.
 */
@Component
public class JsonResponse implements Serializable {
    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
