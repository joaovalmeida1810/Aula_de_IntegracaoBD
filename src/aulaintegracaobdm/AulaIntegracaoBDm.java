package aulaintegracaobdm;

import dominio.Empregado;
import java.sql.SQLException;
import negocio.EmpregadoNegocio;
import java.util.Scanner;

public class AulaIntegracaoBDm {

    private static EmpregadoNegocio empregadoNegocio = new EmpregadoNegocio();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("\nMENU DE OPÇOES");
            System.out.println(" 0 - Encerrar app");
            System.out.println(" 1- Listar todos os empregados");
            System.out.println(" 2- Inserir empregado");
            System.out.println("3 - Alterar empregado");
            System.out.println("4- excluir empregado");
            System.out.println("5- Buscar um empregado");
            System.out.println("Informe a opcao:");
            opcao = sc.nextInt();
            switch (opcao) {
                case 0:
                    System.out.println("Encerrando app...");
                    break;
                case 1:
                    buscarTodos();
                    break;
                case 2:
                    System.out.println("\n --Inserir empregado--");
                    System.out.println("Informe o codigo:");
                    int codigo = sc.nextInt();
                    System.out.println("Informe o nome:");
                    String nome = sc.next();
                    System.out.println("Informe o salario:");
                    double salario = sc.nextDouble();
                    Empregado e = new Empregado(codigo, nome, salario);
                    try {
                        empregadoNegocio.inserir(e);
                        System.out.println("Empregado foi inserido com sucesso.");
                    } catch (SQLException sqlex) {
                        System.out.println("Não foi possivel inserir o empregado no bd");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("alterar empregado ");
                    System.out.println("informe o codigo:");
                    codigo = sc.nextInt();
                    System.out.println("informe nome:");
                    nome = sc.next();
                    System.out.println("informe salario:");
                    salario = sc.nextDouble();
                    e = new Empregado(codigo, nome, salario);
                    try {
                        empregadoNegocio.alterarPorCodigo(e);
                        System.out.println("Empregado alterado com sucesso");
                    } catch (SQLException sqlex) {
                        System.out.println("Não foi possivel alterar o empregado");

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("excluir empregado");
                    System.out.println("informe o codigo:");
                    codigo = sc.nextInt();
                    try{
                        empregadoNegocio.excluirPorCodigo(codigo);

                    }catch(SQLException sqlex){
                        System.out.println("Não foi possivel excluir empregado");

                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("\nBuscar um empregado");
                    System.out.println("Informe o código");
                    codigo = sc.nextInt();
                    try{
                        System.out.println(empregadoNegocio.buscarPorCodigo(codigo));
                    }catch (SQLException sqlex){
                        System.out.println(sqlex.getMessage());
                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                default:
                    System.out.println("opção inválida");
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
            System.out.println("\nNão foi possivel buscar " + " os empregados.");
        }
    }

}
