# Lab3_1

## a) Exemplos que usam métodos expressivos de encadiamento AssertJ:
- A_EmployeeRepositoryTest.java: 
    
    assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());

- D_EmployeeRestControllerTemplateIT.java

    assertThat(found).extracting(Employee::getName).containsOnly("bob");

- E_EmployeeRestControllerTemplateIT.java

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");


## b) Exemplo onde é feito *mock* do comportamento do repositório (não deve envolver a base de dados)
- Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));

- Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);

## c) Qual a diferença entre @Mock e @MockBean

**@Mock:**
- Permite a criação de simulações abreviadas.
- Minimiza o código de criação simulado repetitivo.
- Torna a classe de teste mais legível.
- Facilita a leitura do erro de verificação porque o nome do campo é usado para identificar o mock.

**@MockBean:**
- Anotação que pode ser usada para adicionar simulações a um Spring ApplicationContext. Pode ser usado como uma anotação de nível de classe ou em campos em classes @Configuration ou classes de teste que são @RunWith o SpringRunner.

- Mocks podem ser registrados por tipo ou por nome de bean. Qualquer bean único existente do mesmo tipo definido no contexto será substituído pelo mock, se nenhum bean existente for definido, um novo será adicionado.

- Quando @MockBean for utilizado em um campo, além de ser registrado no contexto da aplicação, o mock também será injetado no campo.

## d) Qual o papel do ficheiro "aplication-integrationtest.properties"? Em que condições deve ser usado?

Este arquivo possui algumas especificações/configurações que são utilizadas pelo Spring framework. É usado durante os testes e, neste caso, fornece informações sobre como se conectar com a base de dados.

## e) Quais as principais diferenças entre as três estratégias de testes para aceder a uma API (C, D e E)?

**C:**
- Testa os controllers usando mocks dos serviços necessários, através de uma instância MockMvc

**D:**
- Aqui é feito um teste de integridade, usando todos os componentes e exigindo uma base de dados real, mas ainda usando MockMvc

**E:**
- Parecida à estratégia anterior, mas usa um client HTTP real, TestRestTemplate

