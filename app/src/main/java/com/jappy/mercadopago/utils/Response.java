package com.jappy.mercadopago.utils;

import androidx.annotation.Nullable;

public class Response<T> {

    public Status status;

    @Nullable
    public T data;

    @Nullable
    public Throwable error;

    private Response(Status status, @Nullable T data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static Response buildResponse(Status status, @Nullable Object data, @Nullable Throwable error) {
        return new Response(status, data, error);
    }
}
