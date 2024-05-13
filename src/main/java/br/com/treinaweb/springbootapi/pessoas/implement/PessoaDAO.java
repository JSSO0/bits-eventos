package br.com.treinaweb.springbootapi.pessoas.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.treinaweb.springbootapi.pessoas.atribuicoes.PessoasDefinicoes;
import br.com.treinaweb.springbootapi.general.sqlUtil.SqlUtil;
import br.com.treinaweb.springbootapi.pessoas.entity.Pessoa;


public class PessoaDAO {
    private final Connection connection;
    private final PessoasDefinicoes pessoaMapper;

    public PessoaDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.pessoaMapper = new PessoasDefinicoes();
    }

    public List<Pessoa> listarTodosOsUsuarios() throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Pessoa pessoa = pessoaMapper.mapResultSetToPessoa(resultSet);
                pessoas.add(pessoa);
            }
        }

        return pessoas;
    }

    public void criarUsuarioNormal(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO Usuario ( name,  phone, email, is_adm, password) VALUES ( ?, ?, ?, ?, ?);";
        SqlUtil.executeInsert(sql, connection, pessoa);
    }

    public void criarUsuarioCompany(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO Usuario ( name,  phone, email, is_adm, password, company_name) VALUES ( ?, ?, ?, false, ?,?);";
        SqlUtil.executeInsert(sql, connection, pessoa);
    }


    public Pessoa consultarPessoaPorId(String id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    PessoasDefinicoes pessoaMapper = new PessoasDefinicoes();
                    return pessoaMapper.mapResultSetToPessoa(resultSet);
                } else {
                    return null;
                }
            }
        }
    }

    public void atualizarPessoaNormal(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE Usuario SET name = ?, phone = ?, email = ?, is_adm = false, password = ? WHERE id = ?";

        SqlUtil.executeInsert(sql, connection, pessoa);
    }

    public void atualizarPessoaCompany(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE Usuario SET name = ?, phone = ?, email = ?, is_adm = false, password = ?, company_name = ? WHERE id = ?";

        SqlUtil.executeInsert(sql, connection, pessoa);
    }

    public void excluirPessoa(String id) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
