package com.PruebaTia.PruebaTia.Entity;

public class ResponseGeneric<T> {
    private T data;
    private Boolean error;
    private String message;

    // Constructor sin argumentos
    public ResponseGeneric() {
    }

    // Constructor con argumentos
    public ResponseGeneric(T data, Boolean error, String message) {
        this.data = data;
        this.error = error;
        this.message = message;
    }

    // Getters y Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> ResponseGeneric<T> success(T data) {
        return new ResponseGeneric<>(data, false, "Operación exitosa");
    }

    public static <T> ResponseGeneric<T> error(String message) {
        return new ResponseGeneric<>(null, true, message);
    }
}

