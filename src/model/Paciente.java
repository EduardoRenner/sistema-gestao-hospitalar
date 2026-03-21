package model;

import java.util.ArrayList;

public abstract class Paciente {
    private int id;
    protected String nome;
    protected String cpf;



    public Paciente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;

    }

    public abstract double calcularValorFinal();

    public abstract void imprimirPaciente();

    @Override
    public String toString() {
        return "Nome: " + nome +
                ", CPF: " + cpf;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setId(int id) {
        this.id = id;
    }
}
