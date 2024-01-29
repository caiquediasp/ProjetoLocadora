package com.locadora.ProjetoLocadora.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CpfInvalidoException extends RuntimeException{
    public CpfInvalidoException(String mensagem) {
        super(mensagem);
    }
}
