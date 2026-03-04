package model;

import java.util.ArrayList;

public abstract class Paciente {
    protected String nome;
    protected String cpf;
    protected ArrayList<Atendimento> atendimentos;


    public Paciente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.atendimentos = new ArrayList<>();
    }

    public abstract double calcularValorFinal();

    public abstract void imprimirPaciente();

    public void adicionarAtendimento(Atendimento atendimento) {
        this.atendimentos.add(atendimento);
    }

    public void listAtendimentos() {
        int numConsulta = 1;
        for (Atendimento atendimento : atendimentos) {
            System.out.println("Paciente: " + this.nome);
            System.out.println("Cpf: " + this.cpf);
            System.out.println("Consulta n: " + numConsulta);
            System.out.println("Valor da consulta: R$" + calcularValorFinal());
            System.out.println("Data: " + atendimento.getData());
            System.out.println("Descrição: \n" + atendimento.getDescricao());
            numConsulta++;
        }
    }

    public void calcularTotalGeral() {
        double total = 0;
        for (Atendimento atendimento : atendimentos) {
            total += calcularValorFinal();
        }

    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public ArrayList<Atendimento> getAtendimentos() {
        return atendimentos;
    }
}
