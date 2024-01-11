package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.util.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {

    @Query(value = "SELECT * FROM tb_endereco e WHERE e.cep = :cep and e.bairro = :bairro and e.rua = :rua and e.numero = :numero",
            nativeQuery = true)
    Endereco verificarEnderecoExistente(
            @Param("cep") String cep, @Param("bairro") String bairro, @Param("rua") String rua, @Param("numero") Integer numero);


}
