package br.com.treinaweb.springbootapi.company.atribuicoes;

import br.com.treinaweb.springbootapi.company.entity.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDefinicoes {
    public Company mapResultSetToCompany(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getString("id"));
        company.setName(resultSet.getString("name"));
        company.setDescription(resultSet.getString("description"));
        company.setCreated_at(resultSet.getString("created_at"));
        return company;
    }

    public void copiarAtributos(Company destino, Company origem) {
        destino.setName(origem.getName());
        destino.setDescription(origem.getDescription());
        destino.setCreated_at(origem.getCreated_at());
    }
}
