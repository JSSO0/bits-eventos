package br.com.treinaweb.springbootapi.company.implement;

import br.com.treinaweb.springbootapi.company.entity.Company;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

class CompanyDAOTest {
    @Test
    public void testListarTodasAsCompanys() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn("Nome da Empresa");
        when(resultSet.getString("description")).thenReturn("Descrição da Empresa");

        CompanyDAO companyDAO = new CompanyDAO(connection);

        List<Company> companys = companyDAO.listarTodasAsCompanys();

        verify(preparedStatement).executeQuery();

        assertEquals(1, companys.size());
        assertEquals("Nome da Empresa", companys.get(0).getName());
        assertEquals("Descrição da Empresa", companys.get(0).getDescription());
    }

}