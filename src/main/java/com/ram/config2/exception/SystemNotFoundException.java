package com.ram.config2.exception;

public class SystemNotFoundException extends RuntimeException{
    public SystemNotFoundException (Long id) {
        super("could not find System"+id);
    }
}
