package com.locadora.ProjetoLocadora;

import com.locadora.ProjetoLocadora.repository.EstoqueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


public class ProjetoLocadoraApplicationTests {

	@Autowired
	EstoqueRepository repository;

	@Test
	public void testeRepository() {
		Assert.isTrue(repository.verificaEstoqueAndaime(1, 250), "Certo");
	}

}
