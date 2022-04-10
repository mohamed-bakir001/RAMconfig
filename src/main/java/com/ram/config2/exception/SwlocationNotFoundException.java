package com.ram.config2.exception;

public class SwlocationNotFoundException extends Exception {
    public SwlocationNotFoundException (Long id) {
        super("could not find Swlocation"+id);
    }

}
