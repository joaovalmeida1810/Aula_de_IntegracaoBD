package aulaintegracaobdm;

import dominio.Empregado;
import java.sql.SQLException;
import negocio.EmpregadoNegocio;
import java.util.Scanner;

public class AulaIntegracaoBDm {

    private static EmpregadoNegocio empregadoNegocio
            = new EmpregadoNegocio();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("\nMENU DE OPÇÕES");
            System.out.println("0- Encerrar app");
            System.out.println("1- Listar todos empregados");
            System.out.println("2- Inserir empregado");
            System.out.println("3- Alterar empregado");
            System.out.println("4- Excluir empregado");
            System.out.println("5- Buscar um empregado");
            System.out.print("Informe a opção: ");
            opcao = sc.nextInt();
            switch (opcao) {
                case 0:
                    System.out.println("\nEncerrando app...");
                    break;
                case 1:
                    buscarTodos();
                    break;
                case 2:
                    System.out.println("\nInserir empregado");
                    System.out.print("Informe o código: ");
                    int codigo = sc.nextInt();
                    System.out.print("Informe o nome: ");
                    String nome = sc.next();
                    System.out.print("Informe o salário: ");
                    double salario = sc.nextDouble();
                    Empregado e
                            = new Empregado(codigo, nome, salario);
                    try {
                        empregadoNegocio.inserir(e);
                        System.out.println("\nEmpregado inserido "
                                + "com sucesso.");
                    } catch (SQLException sqlex) {
                        System.out.println("\nNão foi possivel "
                                + "inserir o empregado no BD.");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("\nAlterar empregado");
                    System.out.print("Informe o código: ");
                    codigo = sc.nextInt();
                    System.out.print("Informe o nome: ");
                    nome = sc.next();
                    System.out.print("Informe o salário: ");
                    salario = sc.nextDouble();
                    e = new Empregado(codigo, nome, salario);
                    try {
                        empregadoNegocio.alterarPorCodigo(e);
                        System.out.println("\nEmpregado "
                                + "alterado com sucesso.");
                    } catch (SQLException sqlex) {
                        System.out.println("\nNão foi possível "
                                + "alterar o empregado.");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("\nExcluir empregado");
                    System.out.print("Informe o código: ");
                    codigo = sc.nextInt();
                    try {
                        empregadoNegocio.excluirPorCodigo(codigo);
                        System.out.println("\nEmpregado excluído "
                                + "com sucesso.");
                    } catch (SQLException sqlex) {
                        System.out.println("\nNão foi possível "
                                + "excluir o empregado.");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("\nBuscar um empregado");
                    System.out.print("Informe o código: ");
                    codigo = sc.nextInt();
                    try {
                        e = empregadoNegocio.buscarPorCodigo(codigo);
                        if (e != null){
                            System.out.println(e);
                        }else {
                            System.out.println("Não foi possivel encontrar empregado com esse código");
                        }
                    } catch (SQLException sqlex) {
                        System.out.println("\nNão foi possível "
                                + "buscar o empregado.");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                default:
                    System.out.println("\nOpção inválida");
            }
        } while (opcao != 0);
    }

    public static void buscarTodos() {
        System.out.println("\nLista de Empregados:");
        try {
            for (Empregado e : empregadoNegocio.buscarTodos()) {
                System.out.println(e);
            }
        } catch (SQLException sqlex) {
            System.out.println("\nNão foi possível buscar "
                    + "os empregados.");
        }
    }

}