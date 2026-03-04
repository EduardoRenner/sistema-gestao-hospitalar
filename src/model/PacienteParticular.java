package model;

public class PacienteParticular extends Paciente {
    public PacienteParticular(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public double calcularValorFinal() {
        double total = 0;

        for (Atendimento atendimento : atendimentos) {
            total += atendimento.getValorBase();
        }
        double taxaAdministrativa = total * 0.10;

        return total + taxaAdministrativa;
    }

    @Override
    public void imprimirPaciente() {
        System.out.println("Nome do paciente: "+getNome());
        System.out.println("Inscrito no cpf: "+getCpf());
        System.out.println("Cadastro particular.");
    }
}
