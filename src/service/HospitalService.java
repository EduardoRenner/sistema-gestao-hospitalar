package service;

import dao.PacienteDAO;
import exception.CoberturaInvalidaException;
import model.*;

import java.time.LocalDate;
import java.util.*;

public class HospitalService {
    Scanner sc = new Scanner(System.in);
    private List<Paciente> pacientes = new ArrayList<>();

    public void menu() {
    int opcao;
        do {
            System.out.println();
            System.out.println("1 - Cadastrar paciente particular;");
            System.out.println("2 - Cadastrar paciente convênio;");
            System.out.println("3 - Listar pacientes;");
            System.out.println("4 - Processar paciente;");
            System.out.println("5 - Adicionar atendimento a paciente;");
            System.out.println("6 - Quantos antendimentos o paciente possui;");
            System.out.println("7 - Atualizar nome do paciente;");
            System.out.println("8 - Deletar paciente;");
            System.out.println("0 - Sair.");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    criarPacienteParticular();
                    break;
                case 2:
                    criarPacienteConvenio();
                    break;
                case 3:
                    listarPacientes();
                    break;
                case 4:
                    selecionarPacienteMenu();
                    break;
                case 5:
                    adicionarAtendimentoPaciente();
                    break;
                case 6:
                    selecionarPacientesParaListarAtendimentos();
                    break;
                case 7:
                    atualizarPaciente();
                    break;
                case 8:
                    deletarPaciente();
                    break;
                case 0:
                    System.out.println("Processo finalizado.");
                    break;
                default:
                    System.out.println("Número do índice inválido");
                    break;
            }
        } while (opcao != 0);
    }

    public void criarPacienteParticular() {
        String nome = null;
        String cpf = null;
        try {
            System.out.println("Digite o nome do paciente: ");
            nome = sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Esperado: texto; \nInserido: número.");
        }
        try {
            System.out.println("Insira o cpf do paciente: ");
            cpf = sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Esperado: número; \nInserido: texto.");
        }
        PacienteParticular pacienteParticular = new PacienteParticular(nome, cpf);
        PacienteDAO dao = new PacienteDAO();
        dao.inserirPaciente(pacienteParticular);
        pacientes.add(pacienteParticular);
        System.out.println("Paciente cadastrado(a)!");
    }

    public void criarPacienteConvenio() {
        String nome = null;
        String cpf = null;
        int opcaoConvenio = 0;
        try {
            System.out.println("Digite o nome do paciente: ");
            nome = sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Esperado: texto; \nInserido: número.");
        }
        try {
            System.out.println("Insira o cpf do paciente: ");
            cpf = sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Esperado: número; \nInserido: texto.");
        }
        System.out.println("Insira o convênio do paciente: ");
        for (int i = 0; i < Convenio.values().length; i++) {
            System.out.println(i + 1 + " - " + Convenio.values()[i].name());
        }
        try {
            opcaoConvenio = sc.nextInt() - 1;
            sc.nextLine();
            if (opcaoConvenio < 0 || opcaoConvenio >= Convenio.values().length) {
                System.out.println("Opção inválida");
                menu();
                return;
            }
            Convenio convenio = Convenio.values()[opcaoConvenio];
            PacienteConvenio pacienteConvenio = new PacienteConvenio(nome, cpf, convenio);
            PacienteDAO dao = new PacienteDAO();
            dao.inserirPaciente(pacienteConvenio);
            pacientes.add(pacienteConvenio);
            System.out.println("Paciente cadastrado(a)!");
        }catch (InputMismatchException e) {
            System.out.println("Esperado: número; \nInserido: texto.");
        } catch (Exception e) {
            System.out.println("Entrada inválida");
            sc.nextLine();
        }
    }

    public void selecionarPacienteMenu(){
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado");
            return;
        }
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + " - " + pacientes.get(i).getNome());
        }
        int indice = sc.nextInt() - 1;
        sc.nextLine();
        if (indice < 0 || indice >= pacientes.size()) {
            System.out.println("Opção inválida");
            return;
        }
        Paciente pacienteSelecionado = pacientes.get(indice);
        processarPaciente(pacienteSelecionado);
    }

    public void processarPaciente(Paciente paciente) {
        try {

            System.out.println("Paciente: " + paciente.getNome());
            System.out.println("Cpf: " + paciente.getCpf());
            if (paciente instanceof PacienteParticular) {
                System.out.println("Tipo: Paciente Particular");
            } else if (paciente instanceof PacienteConvenio) {
                System.out.println("Tipo: Paciente Convênio");
            }

            paciente.listarAtendimentosPaciente();

            double valorFinal = paciente.calcularValorFinal();

            System.out.println("Valor total a ser pago: R$" + valorFinal);

        } catch (CoberturaInvalidaException e) {
            System.out.println("Erro ao processar paciente: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

    }

    public void listarPacientes(){
        PacienteDAO dao = new PacienteDAO();
        dao.listarPacientes();
    }

    public void adicionarAtendimentoPaciente(){
        if (pacientes.isEmpty()){
            System.out.println("Não há pacientes para adicionar atendimentos");
            return;
        }
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i+1)+" - "+pacientes.get(i).getNome());
        }
        int indice = sc.nextInt() - 1;
        sc.nextLine();
        if (indice < 0 || indice >= pacientes.size()){
            System.out.println("Opção inválida");
            return;
        }
        Paciente pacienteSelecionado = pacientes.get(indice);
        System.out.println("Adicione uma descrição ao atendimento: ");
        String descricao = sc.nextLine();
        System.out.println("Adicione o valor do atendimento: ");
        double valor = sc.nextDouble();
        sc.nextLine();
        Atendimento atendimento = new Atendimento(descricao, valor);
        pacienteSelecionado.adicionarAtendimento(atendimento);
        System.out.println("Atendimento adicionado com sucesso!");
    }

    public void selecionarPacientesParaListarAtendimentos(){
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado");
            return;
        }
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + " - " + pacientes.get(i).getNome());
        }
        int indice = sc.nextInt() - 1;
        sc.nextLine();
        if (indice < 0 || indice > pacientes.size()) {
            System.out.println("Opção inválida");
            return;
        }
        Paciente pacienteSelecionado = pacientes.get(indice);
        pacienteSelecionado.listarAtendimentosPaciente();
    }

    public void deletarPaciente(){
        PacienteDAO dao = new PacienteDAO();

        System.out.println("Insira o CPF completo do paciente que deseja deletar do banco: ");
        String cpf = sc.nextLine();

        dao.deletePaciente(cpf);
    }

    public void atualizarPaciente(){
        PacienteDAO dao = new PacienteDAO();

        System.out.println("Digite o CPF do paciente: ");
        String cpf = sc.nextLine();
        System.out.println("Digite o novo nome do paciente");
        String novoNome = sc.nextLine();

        dao.atualizarPaciente(cpf, novoNome);
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

}
