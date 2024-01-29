package com.locadora.ProjetoLocadora.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContratanteNaoEncontradoException extends RuntimeException {
    public ContratanteNaoEncontradoException() {
        ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contratante não encontrado!");
    }
}
