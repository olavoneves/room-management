package br.com.room_management.infra.exceptions;

public class ErrorValidationException extends RuntimeException {
    public ErrorValidationException(String message) {
        super(message);
    }
}
