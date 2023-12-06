package com.meusite.animex.exceptions.exception;

import com.meusite.animex.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends CustomException {

    public EmailNotFoundException(String email) {
        super("Usuário não encontrado com o email: " + email, HttpStatus.NOT_FOUND);
    }
}
