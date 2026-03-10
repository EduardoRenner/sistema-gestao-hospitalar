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

    public void listarAtendimentosPaciente(){
        if (this.getAtendimentos().isEmpty()){
            System.out.println("Nenhum atendimento cadastrado.");
        }else {
            int i = 1;
            System.out.println("Paciente: "+this.nome);
            for (Atendimento atendimento : atendimentos) {
            System.out.println("Atendimento "+i+":");
                atendimento.imprimirAtendimento();
                i++;
            }
            System.out.println("\nO paciente "+this.nome+" possui "+atendimentos.size()+" atendimento(s) no total");
        }
    }

    @Override
    public String toString() {
        return "Nome: "+nome +
                ", CPF: "+cpf;
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
