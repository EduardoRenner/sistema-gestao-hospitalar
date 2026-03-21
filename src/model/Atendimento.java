package model;

import java.time.LocalDate;

public class Atendimento {
    private String descricao;
    private double valorBase;
    private LocalDate data;
    private int pacienteId;

    public Atendimento(String descricao, double valorBase, LocalDate data, int paciente_id) {
        this.descricao = descricao;
        this.valorBase = valorBase;
        this.data = data;
        this.pacienteId = paciente_id;
    }

    public double calcularValorFinal() {
        return valorBase;
    }

    public void imprimirAtendimento() {
        System.out.println(this.descricao);
        System.out.println(this.valorBase);
        System.out.println(this.data);
    }

    @Override
    public String toString() {
        return "Descrição: " + descricao +
                ", valor base: R$" + valorBase;
    }


    public String getDescricao() {
        return descricao;
    }

    public double getValorBase() {
        return valorBase;
    }

    public String getData() {
        return data.toString();
    }

    public int getPacienteId() {
        return pacienteId;
    }
}
