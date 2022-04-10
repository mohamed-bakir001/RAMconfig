package com.ram.config2.exception;

public class AirplanNotFoundException extends RuntimeException {
     public AirplanNotFoundException (Long id) {
         super("could not find Airplan"+id);
     }
}
