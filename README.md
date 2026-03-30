# Sistema de Gestão Hospitalar

Aplicação CLI em Java para gerenciamento de pacientes e atendimentos hospitalares, com persistência em banco de dados SQLite e arquitetura em camadas.

---

## Sobre o Projeto

Aplicação de linha de comando que permite gerenciar pacientes e atendimentos de uma unidade hospitalar. O sistema diferencia pacientes **particulares** e pacientes com **convênio**, aplicando regras de negócio específicas para cada tipo no cálculo do valor final.

---

## Funcionalidades

**Gestão de Pacientes**
- Cadastro de pacientes particulares e com convênio
- Listagem de todos os pacientes
- Atualização do nome do paciente
- Exclusão de paciente

**Processamento de Pacientes**
- Visualização de dados e atendimentos do paciente
- Processamento de todos os atendimentos com cálculo do valor final
- Processamento de um atendimento específico com cálculo individual

**Menu de Atendimentos**
- Adição de atendimento vinculado a um paciente
- Listagem de atendimentos por paciente
- Atualização da descrição de um atendimento
- Exclusão de atendimento

---

## Regras de Negócio

**Paciente Particular:**
- Valor total dos atendimentos acrescido de 10% de taxa administrativa

**Paciente Convênio:**
- Valor total dos atendimentos com desconto conforme o percentual de cobertura:

| Convênio | Cobertura |
|---|---|
| UNIMED | 70% |
| AMIL | 60% |
| BRADESCO | 50% |

- Convênios com cobertura abaixo de 50% lançam uma `CoberturaInvalidaException`

---

## Arquitetura

```
src/
├── app/
│   └── Main.java                       # Ponto de entrada da aplicação
├── database/
│   ├── DatabaseConnection.java         # Gerenciamento de conexão com SQLite
│   └── DatabaseInitializer.java        # Criação automática das tabelas no banco
├── dao/
│   ├── PacienteDAO.java                # CRUD completo de pacientes
│   └── AtendimentoDAO.java             # CRUD completo de atendimentos
├── model/
│   ├── Paciente.java                   # Classe abstrata base
│   ├── PacienteParticular.java         # Paciente sem convênio
│   ├── PacienteConvenio.java           # Paciente com convênio
│   ├── Atendimento.java                # Modelo de atendimento
│   └── Convenio.java                   # Enum com tipos e percentuais de cobertura
├── service/
│   └── HospitalService.java            # Lógica de negócio e menus interativos
└── exception/
    └── CoberturaInvalidaException.java # Exceção customizada de domínio
```

---

## Banco de Dados

O banco SQLite é inicializado automaticamente na primeira execução.

**Tabela `paciente`**
| Coluna | Tipo | Descrição |
|---|---|---|
| id | INTEGER PK | Identificador único |
| nome | TEXT | Nome do paciente |
| cpf | TEXT | CPF do paciente |
| convenio | TEXT | Tipo de convênio ou "PARTICULAR" |

**Tabela `atendimento`**
| Coluna | Tipo | Descrição |
|---|---|---|
| id | INTEGER PK | Identificador único |
| descricao | TEXT | Descrição do atendimento |
| valorBase | REAL | Valor base do atendimento |
| data | TEXT | Data do atendimento (formato ISO) |
| paciente_id | INTEGER FK | Referência ao paciente |

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

**Pré-requisitos:**
- Java 17 ou superior instalado
- Driver JDBC do SQLite adicionado como dependência

**Passos:**

1. Clone o repositório:
```bash
git clone https://github.com/EduardoRenner/sistema-gestao-hospitalar.git
```

2. Abra o projeto no IntelliJ IDEA

3. Certifique-se de que o arquivo `sqlite-jdbc-3.51.2.0.jar` da pasta `lib/` está adicionado como biblioteca (`Add as Library`)

4. Execute a classe `Main.java`

O banco de dados `hospital.db` será criado automaticamente na raiz do projeto.

---

## Conceitos e Padrões Aplicados

- **Programação Orientada a Objetos**: herança, polimorfismo, encapsulamento e abstração
- **Padrão DAO** (Data Access Object): separação entre lógica de negócio e acesso a dados
- **Exceções customizadas**: tratamento de erros específicos do domínio de negócio
- **Enum com comportamento**: convênios com percentuais de cobertura encapsulados
- **JDBC com PreparedStatement**: prevenção de SQL Injection e boas práticas de acesso ao banco
- **Chave estrangeira**: relacionamento relacional entre tabelas `paciente` e `atendimento`
- **RETURN_GENERATED_KEYS**: recuperação do id gerado automaticamente pelo banco após inserção

---

## Autor

**Eduardo Renner**  
Desenvolvedor em formação
