package com.locadora.ProjetoLocadora.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContratanteNaoEncontrado extends RuntimeException {
    public ContratanteNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}
