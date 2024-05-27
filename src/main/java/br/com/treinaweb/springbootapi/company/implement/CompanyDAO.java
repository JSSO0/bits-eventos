package br.com.treinaweb.springbootapi.company.implement;

import br.com.treinaweb.springbootapi.company.atribuicoes.CompanyDefinicoes;
import br.com.treinaweb.springbootapi.general.sqlUtil.SqlUtil;
import br.com.treinaweb.springbootapi.company.entity.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CompanyDAO {
    private final Connection connection;
    private CompanyDefinicoes companyMapper;

    public CompanyDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.companyMapper = new CompanyDefinicoes();
    }

    public void criarEmpresa(Company empresa) throws SQLException {
        String sql = "INSERT INTO Company (name, description) VALUES (?, ?);";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, empresa.getName());
            pstmt.setString(2, empresa.getDescription());

            System.out.println("Query SQL preenchida: " + pstmt.toString());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Em caso de erro ao executar a inserção, imprimir uma mensagem de erro e o stack trace
            System.err.println("Erro ao executar a inserção no banco de dados:");
            e.printStackTrace();
        }
    }

    public List<Company> listarTodasAsCompanys() throws SQLException{
        List<Company> companys = new ArrayList<>();
        String sql = "SELECT * FROM Company";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()){
            Company company = companyMapper.mapResultSetToCompany(resultSet);
            companys.add(company);
        }
        return companys;
        }
    }
    public Company consultarEmpresa(String id) throws SQLException {
        String sql = "SELECT * FROM Company WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    CompanyDefinicoes companyMapper = new CompanyDefinicoes();
                    return companyMapper.mapResultSetToCompany(resultSet);
                } else {
                    return null;
                }
            }
        }
    }

    public void atualizarEmpresa(Company empresa) throws SQLException {
        String sql = "UPDATE Company SET name = ?, description = ? WHERE id = ?;";
        SqlUtil.executeInsert(sql, connection, empresa);
    }

    public void deletarEmpresa(String id) throws SQLException {
        String sql = "DELETE FROM Company WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }
}
