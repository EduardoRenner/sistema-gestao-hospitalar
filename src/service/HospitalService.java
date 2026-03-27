package service;

import dao.AtendimentoDAO;
import dao.PacienteDAO;
import database.DatabaseConnection;
import exception.CoberturaInvalidaException;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class HospitalService {
    Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println();
            System.out.println("1 - Cadastrar paciente particular;");
            System.out.println("2 - Cadastrar paciente convênio;");
            System.out.println("3 - Listar pacientes;");
            System.out.println("4 - Processar paciente;");
            System.out.println("5 - Menu de atendimentos;");
            System.out.println("6 - Atualizar nome do paciente;");
            System.out.println("7 - Deletar paciente;");
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
                    menuAtendimentos();
                    break;
                case 6:
                    atualizarPaciente();
                    break;
                case 7:
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

            cpf = cpf.replaceAll("[^0-9]", "");

        } catch (InputMismatchException e) {
            System.out.println("Esperado: número; \nInserido: texto.");
        }
        PacienteParticular pacienteParticular = new PacienteParticular(nome, cpf);
        PacienteDAO dao = new PacienteDAO();
        int id = dao.inserirPaciente(pacienteParticular);
        pacienteParticular.setId(id);

        System.out.println("Paciente cadastrado(a)!");
    }

    public void criarPacienteConvenio() {
        String nome;
        String cpf = null;
        int opcaoConvenio = 0;
        System.out.println("Digite o nome do paciente: ");
        nome = sc.nextLine();

        if (nome.trim().isEmpty()) {
            System.out.println("Não é possível cadastrar sem nome.");
            return;
        }

        System.out.println("Digite o CPF completo do paciente:");
        cpf = sc.nextLine();

        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.trim().isEmpty()) {
            System.out.println("Não é possível cadastrar sem CPF.");
            return;
        }

        System.out.println("Insira o convênio do paciente: ");
        for (int i = 0; i < Convenio.values().length; i++) {
            System.out.println(i + 1 + " - " + Convenio.values()[i].name());
        }

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
        int id = dao.inserirPaciente(pacienteConvenio);
        pacienteConvenio.setId(id);

        System.out.println("Paciente cadastrado(a)!");
    }

    public void selecionarPacienteMenu() {
        Paciente paciente = selecionarPaciente();

        if (paciente == null) {
            System.out.println("Paciente inválido.");
            return;
        }

        processarPaciente(paciente);
    }

    public void processarPaciente(Paciente paciente) {
        try {
            System.out.println("ID: " + paciente.getId());
            System.out.println("Paciente: " + paciente.getNome());
            System.out.println("Cpf: " + paciente.getCpf());
            if (paciente instanceof PacienteParticular) {
                System.out.println("Tipo: Paciente Particular");
            } else if (paciente instanceof PacienteConvenio) {
                System.out.println("Tipo: Paciente " + ((PacienteConvenio) paciente).getConvenio());
            }
            AtendimentoDAO dao = new AtendimentoDAO();
            dao.listarAtendimentosPaciente(paciente.getId());

            System.out.println("Escolha a forma de processamento: ");

            int opcao;

            System.out.println("1 - Processar todos os atendimentos;");
            System.out.println("2 - Processar atendimento individual;");
            System.out.println("0 - Retornar ao menu.");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("Valor total a ser pago: R$" + paciente.calcularValorFinal());
                    menu();
                    break;
                case 2:
                    Atendimento atendimento = dao.selecionarAtendimento();
                    System.out.println("Valor total a ser pago: R$" + paciente.calcularValorUnico(atendimento.getId()));
                    break;
                case 0:
                    menu();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    menu();
                    break;
            }


        } catch (CoberturaInvalidaException e) {
            System.out.println("Erro ao processar paciente: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    public void menuAtendimentos(){
        AtendimentoDAO dao = new AtendimentoDAO();
        int opcao;
        do {
            System.out.println("1 - Adicionar atendimento;");
            System.out.println("2 - Listar atendimentos;");
            System.out.println("3 - Atualizar atendimento;");
            System.out.println("4 - Deletar atendimento;");
            System.out.println("0 - Voltar ao menu.");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){

                case 1:
                    adicionarAtendimentoPaciente();
                    break;
                case 2:
                    selecionarPacientesParaListarAtendimentos();
                    break;
                case 3:
                    atualizarAtendimento();
                    break;
                case 4:
                    dao.selecionarAtendimento();
                    deletarAtendimento();
                    break;
                default:
                    System.out.println("Opção de índice inválida");
                    break;
            }
        }while (opcao != 0);
    }

    public void listarPacientes() {
        PacienteDAO dao = new PacienteDAO();
        dao.listarPacientes();
    }

    public void adicionarAtendimentoPaciente() {
        Paciente paciente = selecionarPaciente();


        if (paciente == null) {
            System.out.println("Paciente inválido.");
            return;
        }

        System.out.println("Insira a descrição do atendimento: ");
        String descricao = sc.nextLine();

        System.out.println("Insira o valor do atendimento(R$) :");
        double valorBase = sc.nextDouble();
        sc.nextLine();

        LocalDate data = LocalDate.now();
        Atendimento atendimento = new Atendimento(descricao, valorBase, data, paciente.getId());


        AtendimentoDAO dao = new AtendimentoDAO();
        int id = dao.inserirAtendimento(atendimento);
        atendimento.setId(id);

        System.out.println("Atendimento salvo com sucesso!");
    }

    public void selecionarPacientesParaListarAtendimentos() {
        Paciente paciente = selecionarPaciente();

        if (paciente == null) {
            System.out.println("Paciente inválido.");
            return;
        }

        AtendimentoDAO dao = new AtendimentoDAO();
        dao.listarAtendimentosPaciente(paciente.getId());

    }

    public Paciente selecionarPaciente() {
        int id;
        String nome = null;
        String cpf = null;
        String convenio = null;

        boolean founded = false;

        String sql = "SELECT * FROM paciente";

        try (Connection conn = DatabaseConnection.connect()) {
            PreparedStatement pstmt1 = conn.prepareStatement(sql);
            ResultSet resultSet1 = pstmt1.executeQuery();

            while (resultSet1.next()) {
                founded = true;

                id = resultSet1.getInt("id");
                nome = resultSet1.getString("nome");
                cpf = resultSet1.getString("cpf");
                convenio = resultSet1.getString("convenio");
                System.out.println("ID: " + id + " | Nome: " + nome + " | CPF: " + cpf + " | Convênio: " + convenio);
            }

            if (!founded) {
                System.out.println("Nenhum paciente encontrado.");
                return null;
            }

            String selecionar = "SELECT * FROM paciente WHERE id = ?";
            PreparedStatement pstmt2 = conn.prepareStatement(selecionar);

            System.out.println("Digite o ID do paciente desejado: ");
            int selecionado = sc.nextInt();
            sc.nextLine();

            pstmt2.setInt(1, selecionado);
            ResultSet resultSet2 = pstmt2.executeQuery();

            if (resultSet2.next()) {
                id = resultSet2.getInt("id");
                nome = resultSet2.getString("nome");
                cpf = resultSet2.getString("cpf");
                convenio = resultSet2.getString("convenio");
                if (convenio.equals("PARTICULAR")) {
                    PacienteParticular pacienteParticular = new PacienteParticular(nome, cpf);
                    pacienteParticular.setId(id);
                    return pacienteParticular;
                } else {
                    PacienteConvenio pacienteConvenio = new PacienteConvenio(nome, cpf, Convenio.valueOf(convenio));
                    pacienteConvenio.setId(id);
                    return pacienteConvenio;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deletarPaciente() {
        PacienteDAO dao = new PacienteDAO();

        System.out.println("Insira o CPF completo do paciente que deseja deletar do banco: ");
        String cpf = sc.nextLine();

        dao.deletePaciente(cpf);
    }

    public void deletarAtendimento(){
        AtendimentoDAO dao = new AtendimentoDAO();

        System.out.println("Insira o ID do atendimento que deseja excluir: ");
        int id = sc.nextInt();
        sc.nextLine();

        dao.deletarAtendimento(id);
    }

    public void atualizarPaciente() {
        PacienteDAO dao = new PacienteDAO();

        System.out.println("Digite o CPF do paciente: ");
        String cpf = sc.nextLine();
        System.out.println("Digite o novo nome do paciente");
        String novoNome = sc.nextLine();

        dao.atualizarPaciente(cpf, novoNome);
    }

    public void atualizarAtendimento(){
        AtendimentoDAO dao = new AtendimentoDAO();

        System.out.println("Digite o ID do atendimento: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Insira a nova descrição do atendimento: ");
        String novaDescricao = sc.nextLine();

        dao.atualizarAtendimento(id, novaDescricao);
    }
}
