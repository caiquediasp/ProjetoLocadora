package com.locadora.ProjetoLocadora.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ContratanteNaoEncontradoException extends RuntimeException {
    public ContratanteNaoEncontradoException(String cpf) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND
                , "Contratante não encontrado com o CPF: " + cpf);
    }
}
