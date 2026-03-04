# Sistema de Gestão Hospitalar
## Descrição

Sistema desenvolvido em Java para simular a gestão de pacientes e atendimentos em um hospital.
O projeto utiliza conceitos fundamentais de Programação Orientada a Objetos, como:

Herança

Polimorfismo

Encapsulamento

Enum

Tratamento de exceções personalizadas

Estrutura de menu interativo

## Funcionalidades

Cadastro de pacientes particulares

Cadastro de pacientes com convênio

Associação de atendimentos a pacientes

Cálculo de valor total de atendimentos

Aplicação de desconto por percentual de convênio

Validação de cobertura mínima

Tratamento de exceção personalizada (CoberturaInvalidaException)

## Estrutura do Projeto

src/
 ├── model/
 │    ├── Paciente
 │    ├── PacienteParticular
 │    ├── PacienteConvenio
 │    ├── Atendimento
 │    └── Convenio (enum)
 │
 ├── service/
 │    └── HospitalService
 │
 ├── exception/
 │    └── CoberturaInvalidaException
 │
 └── Main
## Conceitos Aplicados

O sistema utiliza polimorfismo para permitir que cada tipo de paciente implemente sua própria regra de cálculo:

Paciente particular → soma total dos atendimentos 

Paciente convênio → aplica percentual de cobertura
