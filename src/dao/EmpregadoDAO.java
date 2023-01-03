package dao;

import dominio.Empregado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpregadoDAO {

    private String url = "jdbc:mysql://localhost:3306/empresa";
    private String usuario = "root";
    private String senha = "";

    public Connection conectar() {
        try {

            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            return conexao;
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        }
        return null;
    }

    public void desconectar(Connection conexao) {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }

        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        }

    }

    public List<Empregado> buscarTodos() throws SQLException {
        List<Empregado> empregados = new ArrayList<>();
        Connection conexao = conectar();
        String sql = "SELECT * FROM empregado";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int codigo = rs.getInt("codigo");
            String nome = rs.getString("nome");
            double salario = rs.getDouble("salario");
            Empregado e = new Empregado(codigo, nome, salario);
            empregados.add(e);
        }
        desconectar(conexao);
        return empregados;
    }

    public void inserir(Empregado empregado) throws SQLException {
        Connection conexao = conectar();
        String sql = "INSERT INTO empregado  VALUES (?, ?, ?)";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, empregado.getCodigo());
        ps.setString(2, empregado.getNome());
        ps.setDouble(3, empregado.getSalario());
        ps.executeUpdate();
        desconectar(conexao);
    }
    public void alterarPorCodigo(Empregado empregado) throws SQLException{
        Connection conexao = conectar();
        String sql = "UPDATE empregado SET nome = ?," + "salario = ? WHERE codigo = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, empregado.getCodigo());
        ps.setString(2, empregado.getNome());
        ps.setDouble(3, empregado.getSalario());
        ps.executeUpdate();
        desconectar(conexao);
    }
    public void excluir(int codigo) throws SQLException{
        Connection conexao = conectar();
        String sql = "DELETE FROM empregado WHERE codigo = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, codigo);
        ps.executeUpdate();
        desconectar(conexao);
    }
    public Empregado buscarPorCodigo(int codigo) throws SQLException{
        Empregado e = null;
        Connection conexao = conectar();
        String sql = "SELECT * FROM empregado WHERE codigo = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();
        ps.executeUpdate();
        if(rs.next()){
            String nome = rs.getString("nome");
            double salario = rs.getDouble("salario");
            e = new Empregado(codigo, nome, salario);
        }
        desconectar(conexao);
        return e;
    }

}


