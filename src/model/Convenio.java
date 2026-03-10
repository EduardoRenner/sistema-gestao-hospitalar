package model;

public enum Convenio {

    UNIMED(0.7),
    AMIL(0.6),
    BRADESCO(0.5),
    ERRO(0.3);

    private final double percentualCobertura;

    Convenio(double percentualCobertura) {
        this.percentualCobertura = percentualCobertura;
    }



    public double getPercentualCobertura() {
        return percentualCobertura;
    }

    @Override
    public String toString() {
        return "Convênio: "+ name()+
                ", percentual de cobertura: "+percentualCobertura;
    }
}
