package model;

import exception.CoberturaInvalidaException;

public class PacienteConvenio extends Paciente {
    private Convenio convenio;

    public PacienteConvenio(String nome, String cpf, Convenio convenio) {
        super(nome, cpf);
        this.convenio = convenio;
    }

    @Override
    public double calcularValorFinal() {
        double cobertura = convenio.getPercentualCobertura();
        if (cobertura < 0.5) {
            throw new CoberturaInvalidaException("Cobertura abaixo do mínimo permitido");
        }
        double total = 0;

        for (Atendimento atendimento : atendimentos) {
            total += atendimento.calcularValorFinal ();
        }

        return total * (1 - cobertura);
    }

    @Override
    public void imprimirPaciente() {
        System.out.println("Nome do paciente: "+getNome());
        System.out.println("Inscrito no cpf: "+getCpf());
        System.out.println("Convênio: "+getConvenio());
    }

    @Override
    public String toString() {
        return super.toString()+
        ", tipo: "+ getConvenio();
    }

    public Convenio getConvenio() {
        return convenio;
    }
}
