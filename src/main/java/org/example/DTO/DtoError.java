package org.example.DTO;

import java.lang.reflect.Field;

public class DtoError {

    private final String message;
    private final String name;
    private final String articular;

    public DtoError(String name, String articular, String message) {
        this.message = message;
        this.name = name;
        this.articular =articular;
    }

    public int countFields(){
        Field[] fields = this.getClass().getDeclaredFields();
        return fields.length;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getArticular() {
        return articular;
    }

}
