package com.meusite.animex.exceptions.exception;

import com.meusite.animex.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyInUseException extends CustomException {
    public EmailAlreadyInUseException(String email) {
        super("Email já em uso: " + email, HttpStatus.BAD_REQUEST);
    }
}
