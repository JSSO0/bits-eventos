package br.com.treinaweb.springbootapi.company.atribuicoes;

import br.com.treinaweb.springbootapi.company.entity.Company;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.powermock.api.mockito.PowerMockito.when;

class CompanyDefinicoesTest {

    @Test
    public void testMapResultSetToCompany() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getString("name")).thenReturn("Nome da Empresa");
        when(resultSet.getString("description")).thenReturn("Descrição da Empresa");

        CompanyDefinicoes companyDefinicoes = new CompanyDefinicoes();

        Company company = companyDefinicoes.mapResultSetToCompany(resultSet);

        assertEquals("Nome da Empresa", company.getName());
        assertEquals("Descrição da Empresa", company.getDescription());
    }

    @Test
    public void testCopiarAtributos() {
        Company origem = new Company();
        origem.setName("Empresa Origem");
        origem.setDescription("Descrição da Empresa Origem");

        Company destino = new Company();

        CompanyDefinicoes companyDefinicoes = new CompanyDefinicoes();

        companyDefinicoes.copiarAtributos(destino, origem);

        assertEquals("Empresa Origem", destino.getName());
        assertEquals("Descrição da Empresa Origem", destino.getDescription());
    }

}