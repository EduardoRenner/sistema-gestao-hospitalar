package model;

import dao.AtendimentoDAO;

public class PacienteParticular extends Paciente {
    public PacienteParticular(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public double calcularValorFinal() {
        double total = 0;

        AtendimentoDAO dao = new AtendimentoDAO();
        total = dao.valorTotal(this.getId());

        double taxaAdministrativa = total * 0.10;

        return total + taxaAdministrativa;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", tipo: particular";
    }

    @Override
    public void imprimirPaciente() {
        System.out.println("Nome do paciente: " + getNome());
        System.out.println("Inscrito no cpf: " + getCpf());
        System.out.println("Cadastro particular.");
    }
}
