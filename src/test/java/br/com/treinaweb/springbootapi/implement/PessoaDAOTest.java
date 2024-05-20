<<<<<<< Updated upstream
=======
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.treinaweb.springbootapi.entity.Pessoa;

public class PessoaDAOTest {

    private PessoaDAO pessoaDAO;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Before
    public void setUp() throws SQLException {
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        pessoaDAO = new PessoaDAO(connection);
    }

    @Test
    public void testListarTodosOsUsuarios() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("id")).thenReturn("1");
        when(resultSet.getString("name")).thenReturn("Rafael");
        when(resultSet.getString("phone")).thenReturn("123456789");
        when(resultSet.getString("email")).thenReturn("rafael@example.com");

        List<Pessoa> pessoas = pessoaDAO.listarTodosOsUsuarios();

        assertEquals(1, pessoas.size());
        Pessoa pessoa = pessoas.get(0);
        assertEquals("1", pessoa.getId());
        assertEquals("Rafael", pessoa.getName());
        assertEquals("123456789", pessoa.getPhone());
        assertEquals("rafael@example.com", pessoa.getEmail());
    }

    @Test
    public void testAtualizarPessoaNormal() throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId("1");
        pessoa.setName("Rafael");
        pessoa.setPhone("123456789");
        pessoa.setEmail("rafael@example.com");

        pessoaDAO.atualizarPessoaNormal(pessoa);

        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testAtualizarPessoaCompany() throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId("1");
        pessoa.setName("Rafael");
        pessoa.setPhone("123456789");
        pessoa.setEmail("rafael@example.com");
        pessoa.setCompanyName("ABC Company");

        pessoaDAO.atualizarPessoaCompany(pessoa);

        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testExcluirPessoa() throws SQLException {
        String id = "1";

        pessoaDAO.excluirPessoa(id);

        verify(preparedStatement).executeUpdate();
    }
}
>>>>>>> Stashed changes
