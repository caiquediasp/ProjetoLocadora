package com.locadora.ProjetoLocadora.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CpfInvalidoException extends RuntimeException{
    public CpfInvalidoException(String mensagem) {
        throw new ResponseStatusException(HttpStatus.CONFLICT
                , mensagem);
    }
}
