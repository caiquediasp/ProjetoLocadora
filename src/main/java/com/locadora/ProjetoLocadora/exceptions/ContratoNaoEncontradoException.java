package com.locadora.ProjetoLocadora.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ContratoNaoEncontradoException extends RuntimeException{
    public ContratoNaoEncontradoException(String id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND
                , "Contrato não encontrado com ID: " + id);
    }
}
