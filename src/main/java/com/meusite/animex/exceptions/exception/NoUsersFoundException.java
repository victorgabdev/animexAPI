package com.meusite.animex.exceptions.exception;

import com.meusite.animex.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public class NoUsersFoundException extends CustomException {

    public NoUsersFoundException() {
        super("Nenhum usuário encontrado", HttpStatus.NOT_FOUND);
    }
}
