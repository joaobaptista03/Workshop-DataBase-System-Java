package src.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import src.business.*;
import src.data.OficinaDAO;

public class AdministradorUI {
    
    private OficinaDAO oficinaDAO;
    private BufferedReader reader;

    public AdministradorUI(OficinaDAO oficinaDAO) {
        this.oficinaDAO = oficinaDAO;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void execute() throws IOException {
        try {
            int choice;

            do {
                System.out.println("\nAdministrador Menu:");
                System.out.println("1. Adicionar Funcionário");
                System.out.println("2. Sair");
                System.out.print("Escolha uma opção: ");
                choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1:
                        adicionarFuncionario(oficinaDAO);
                        break;
                    case 2:
                        System.out.println("A sair do Menu Administrador...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (choice != 2);
       
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void adicionarFuncionario(OficinaDAO oficinaDAO) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\nAdicionar Funcionário:");
        
        System.out.print("Nome do Funcionário: ");
        String nome = reader.readLine();
        
        System.out.print("Email do Funcionário: ");
        String email = reader.readLine();
        
        System.out.print("Password do Funcionário: ");
        String password = reader.readLine();
        
        System.out.print("Número do Cartão do Funcionário: ");
        int nrCartao = Integer.parseInt(reader.readLine());
        
        System.out.print("Posto do Funcionário: ");
        String posto = reader.readLine();

        System.out.print("Competências do Funcionário (separadas por vírgula): ");
        String competencias = reader.readLine();
        List<String> competencias_list = Arrays.asList(competencias.split(","));

        int nrFuncionarios = oficinaDAO.getNrFuncionarios();
        Funcionario novoFuncionario = new Funcionario(nrFuncionarios, nome, email, password, nrCartao, posto, competencias_list);
        oficinaDAO.insertFuncionario(novoFuncionario);

        System.out.println("Funcionário adicionado com sucesso!");
    }
}