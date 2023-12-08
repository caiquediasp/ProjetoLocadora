package com.locadora.ProjetoLocadora.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContratoNaoEncontrado extends RuntimeException{
    public ContratoNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}
