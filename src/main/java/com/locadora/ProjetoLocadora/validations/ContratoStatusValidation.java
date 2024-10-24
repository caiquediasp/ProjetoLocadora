package com.locadora.ProjetoLocadora.validations;

import com.locadora.ProjetoLocadora.repository.ContratoRepository;
import com.locadora.ProjetoLocadora.entities.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContratoStatusValidation {
    @Autowired
    private ContratoRepository contratoRepository;

    public void validaStatusContrato() {
        List<Contrato> listaContratos = contratoRepository.validarStatusContratos();

        if(!listaContratos.isEmpty())
            contratoRepository.atualizarStatusContrato();
    }
}
