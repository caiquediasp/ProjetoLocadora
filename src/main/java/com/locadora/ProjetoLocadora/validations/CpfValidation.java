package com.locadora.ProjetoLocadora.validations;

import com.locadora.ProjetoLocadora.exceptions.CpfInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CpfValidation {
    public void validadorCpf(String cpf) throws CpfInvalidoException {
        cpf = cpf.replaceAll("[^0-9]", "");

        if(cpf.length() < 11) {
            throw new CpfInvalidoException("CPF inválido! - Não possui todos os 11 números!");
        } else if (cpf.length() > 11) {
            throw new CpfInvalidoException("CPF inválido! - Possui mais que 11 números!");
        }

        if(cpf.matches("(\\d)\\1{10}")) {
            throw new CpfInvalidoException("CPF inválido! - Possui todos os dígitos iguais!");
        }

        int soma = 0;
        for(int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }

        int primeiroDigito = (soma * 10) % 11;
        if(primeiroDigito == 10 || primeiroDigito != cpf.charAt(10)) {
            throw new CpfInvalidoException("CPF Inválido! - O CPF não existe!");
        }

        for(int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }

        int segundoDigito = (soma * 10) % 11;
        if(segundoDigito == 10 || segundoDigito != cpf.charAt(11)) {
            throw new CpfInvalidoException("CPF Inválido! - O CPF não existe!");
        }
    }
}
