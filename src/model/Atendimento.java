package model;

public class Atendimento {
    private String data;
    private String descricao;
    private double valorBase;

    public Atendimento(String descricao, double valorBase) {
        this.descricao = descricao;
        this.valorBase = valorBase;
    }

    public double calcularValorFinal() {
        return valorBase;
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorBase() {
        return valorBase;
    }
}
