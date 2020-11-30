package com.rezahdrm.cms.model.dto;

public class Message {
    private String message;
    private String nameClass;

    private boolean isError;

    public Message() {
    }

    public Message(String message, String nameClass) {
        this.isError=false;
        this.message = message;
        this.nameClass = nameClass;
    }

    public Message(boolean isError, String message) {
        this.isError = isError;
        this.message = message;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }
}
