package com.klasha.worldapi.datatransfer;

import lombok.Data;

@Data
public class GenericResponse<T> {
    private String message;
    private T data;
}
