package negocio;

import dao.EmpregadoDAO;
import dominio.Empregado;
import java.sql.SQLException;
import java.util.List;

public class EmpregadoNegocio {

    private EmpregadoDAO empregadoDAO
            = new EmpregadoDAO();

    public List<Empregado> buscarTodos()
            throws SQLException {
        return empregadoDAO.buscarTodos();
    }

    public void inserir(Empregado empregado)
            throws SQLException, Exception {
        validaCodigo(empregado.getCodigo());
        validaSalario(empregado.getSalario());
        empregadoDAO.inserir(empregado);
    }

    public void alterarPorCodigo(Empregado empregado)
            throws SQLException, Exception {
        validaCodigo(empregado.getCodigo());
        validaSalario(empregado.getSalario());
        empregadoDAO.alterarPorCodigo(empregado);
    }

    public void excluirPorCodigo(int codigo)
            throws SQLException, Exception {
        validaCodigo(codigo);
        empregadoDAO.excluirPorCodigo(codigo);
    }

    public Empregado buscarPorCodigo(int codigo)
            throws SQLException, Exception{
        validaCodigo(codigo);
        return empregadoDAO.buscarPorCodigo(codigo);
    }

    private void validaCodigo(int codigo)
            throws Exception {
        if (codigo <= 0) {
            throw new Exception("O código deve "
                    + "ser positivo.");
        }
    }

    private void validaSalario(double salario)
            throws Exception{
        if(salario < 1320){
            throw new Exception("O salário não "
                    + "pode ser menor que o "
                    + "salário mínimo.");
        }
    }
}


