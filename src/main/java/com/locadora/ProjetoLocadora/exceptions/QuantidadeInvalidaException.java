package com.locadora.ProjetoLocadora.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuantidadeInvalidaException extends RuntimeException {
    public QuantidadeInvalidaException(String mensagem) {
        throw new ResponseStatusException(HttpStatus.CONFLICT
                , "A quantidade de " + mensagem + " solicitada é maior do que a quantidade em estoque!");
    }
}
