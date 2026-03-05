package model;

public class Atendimento {
    private String descricao;
    private double valorBase;

    public Atendimento(String descricao, double valorBase) {
        this.descricao = descricao;
        this.valorBase = valorBase;
    }

    public double calcularValorFinal() {
        return valorBase;
    }

    public void imprimirAtendimento(){
        System.out.println(this.descricao);
        System.out.println(this.valorBase);
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorBase() {
        return valorBase;
    }
}
