package com.example.demo.service.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class ResponseDTO implements Serializable {

    private String status;

    private String message;

    private Object data;

    public ResponseDTO(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
    public ResponseDTO(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
