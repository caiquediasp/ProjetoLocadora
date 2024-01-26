package com.locadora.ProjetoLocadora.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CpfInvalido extends RuntimeException{
    public CpfInvalido(String mensagem) {
        super(mensagem);
    }
}
