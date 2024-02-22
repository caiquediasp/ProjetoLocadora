# Projeto Locadora

Projeto pessoal criado com a finalidade de estudo e aprendizado,foi feito uma API utilizando Java + Spring, se conectando a um banco de dados PostgreSQL, além de ser documentado com Swagger-UI para facilitar a usabilidade.

## Entidades

- Contrato (Id, Contratante, Endereço, Peças, Data Locação e Devolução, Forma de pagamento, Valor total e Status)
- Contratante (CPF, Nome, Telefone)
- Endereço (Id, CEP, Bairro, Rua, Número)
- Peças  (Valor Total,
  - Andaime (Tamanho, Quantidade)
  - Escora (Tamanho, Quantidade)
  - Plataforma (Tamanho, Quantidade)
  - Roldana (Quantidade)
   
    )

  ### Relacionamento
  - Contrato possui apenas 1 Contratante, 1 Endereço e 1 Peças
  - Contratante, Endereço e Peças precisa de 1 ou vários contratos, não existem sem estarem relacionados à pelo menos 1 contrato
 
## Funcionalidades

  ### Contrato
  
CRUD completo, além de métodos para listar por :
  - Endereço
  - Contratante
  - Contratos Ativos
  - Contratos Vencidos
     
  ### Contratante
  
  Seguintes métodos :
  - Quantidade de contratos do contratante
  - Listar todos contratantes
  - Buscar por CPF
  - Atualizar Telefone
    
  ### Estoque de Peças
  
  Métodos para verificar o estoque de todas as peças :
  - Andaime
  - Escora
  - Plataforma
  - Roldana

## Deploy

Deploy ainda não realizado, estará disponível em breve!
