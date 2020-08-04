package com.application.demo.exceptions;

public class ContactNotFoundExpception extends RuntimeException {
    public ContactNotFoundExpception(int id) {
        super("Could not find employee with this id: " + id);
    }
}
