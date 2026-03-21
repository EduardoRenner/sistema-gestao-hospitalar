
# Sistema de Gestão Hospitalar

> Sistema de gerenciamento de pacientes e atendimentos desenvolvido em Java, com persistência de dados em banco SQLite e arquitetura em camadas.

---

## Sobre o Projeto

O **Sistema de Gestão Hospitalar** é uma aplicação de linha de comando (CLI) desenvolvida para gerenciar pacientes e seus respectivos atendimentos em um ambiente hospitalar. O sistema diferencia pacientes **particulares** e pacientes com **convênio**, aplicando regras de negócio específicas para cada tipo no cálculo do valor final dos atendimentos.

O projeto foi desenvolvido com foco em boas práticas de programação orientada a objetos, separação de responsabilidades e persistência real de dados com banco de dados relacional.

---

## Funcionalidades

-  Cadastro de pacientes particulares e convênio
-  Listagem de todos os pacientes
-  Seleção e processamento de paciente com cálculo de valor final
-  Adição de atendimentos vinculados a um paciente
-  Listagem de atendimentos por paciente
-  Atualização do nome do paciente
-  Exclusão de paciente
-  Persistência completa em banco de dados SQLite

---

## Regras de Negócio

- **Paciente Particular**: valor total dos atendimentos acrescido de 10% de taxa administrativa
- **Paciente Convênio**: valor total dos atendimentos com desconto conforme percentual de cobertura do convênio
  - UNIMED: 70% de cobertura
  - AMIL: 60% de cobertura
  - BRADESCO: 50% de cobertura
- Convênios com cobertura abaixo de 50% lançam uma exceção customizada (`CoberturaInvalidaException`)

---

## Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

```
src/
├── app/
│   └── Main.java                  # Ponto de entrada da aplicação
├── database/
│   ├── DatabaseConnection.java    # Gerenciamento de conexão com SQLite
│   └── DatabaseInitializer.java   # Criação das tabelas no banco
├── dao/
│   ├── PacienteDAO.java           # CRUD de pacientes
│   └── AtendimentoDAO.java        # CRUD de atendimentos
├── model/
│   ├── Paciente.java              # Classe abstrata base
│   ├── PacienteParticular.java    # Paciente sem convênio
│   ├── PacienteConvenio.java      # Paciente com convênio
│   ├── Atendimento.java           # Modelo de atendimento
│   └── Convenio.java              # Enum com tipos de convênio
├── service/
│   └── HospitalService.java       # Lógica de negócio e menu interativo
└── exception/
    └── CoberturaInvalidaException.java  # Exceção customizada
```

---

## Tecnologias Utilizadas

| Tecnologia | Uso |
|---|---|
| Java 17+ | Linguagem principal |
| SQLite | Banco de dados relacional |
| JDBC | Conexão Java com banco de dados |
| IntelliJ IDEA | IDE de desenvolvimento |
| Git & GitHub | Versionamento de código |

---

## Como Executar

### Pré-requisitos

- Java 17 ou superior instalado
- Driver JDBC do SQLite adicionado ao projeto (`sqlite-jdbc`)

### Passos

1. Clone o repositório:
```bash
git clone https://github.com/EduardoRenner/sistema-gestao-hospitalar.git
```

2. Abra o projeto no IntelliJ IDEA

3. Certifique-se de que o driver SQLite está nas dependências do projeto

4. Execute a classe `Main.java`

O banco de dados `hospital.db` será criado automaticamente na raiz do projeto na primeira execução.

---

## Padrões e Conceitos Aplicados

- **Programação Orientada a Objetos**: herança, polimorfismo e abstração
- **Padrão DAO** (Data Access Object): separação entre lógica de negócio e acesso a dados
- **Exceptions customizadas**: tratamento de erros específicos do domínio
- **Enum com comportamento**: convênios com percentuais de cobertura encapsulados
- **JDBC com PreparedStatement**: prevenção de SQL Injection e boas práticas de banco
- **Chave estrangeira**: relacionamento entre tabelas `paciente` e `atendimento`

---

## Autor
Eduardo Renner
**Eduardo Renner**  
Desenvolvedor em formação | Apaixonado por tecnologia e boas práticas de código

[![GitHub](https://img.shields.io/badge/GitHub-EduardoRenner-181717?style=flat&logo=github)](https://github.com/EduardoRenner)
