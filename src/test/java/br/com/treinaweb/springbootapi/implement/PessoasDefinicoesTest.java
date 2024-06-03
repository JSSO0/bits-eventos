package br.com.treinaweb.springbootapi.implement;

//import br.com.treinaweb.springbootapi.entity.Pessoa;
import br.com.treinaweb.springbootapi.pessoas.atribuicoes.PessoasDefinicoes;
import br.com.treinaweb.springbootapi.pessoas.entity.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PessoasDefinicoesTest {

    private PessoasDefinicoes pessoasDefinicoes;
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() {
        pessoasDefinicoes = new PessoasDefinicoes();
        resultSet = mock(ResultSet.class);
    }

    @Test
    public void testMapResultSetToPessoaWithNullValues() throws SQLException {
        // Configuração dos dados simulados do ResultSet com valores nulos
        when(resultSet.getString("id")).thenReturn(null);
        when(resultSet.getString("nome")).thenReturn("Jane Doe");
        when(resultSet.getString("telefone")).thenReturn(null);
        when(resultSet.getString("email")).thenReturn("jane.doe@example.com");
        when(resultSet.getString("password")).thenReturn(null);

        // Chama o método que será testado
        Pessoa pessoa = pessoasDefinicoes.mapResultSetToPessoa(resultSet);

        // Verifica se os atributos foram mapeados corretamente, mesmo com valores nulos
        assertNull(pessoa.getId());
        assertEquals("Jane Doe", pessoa.getName());
        assertNull(pessoa.getPhone());
        assertEquals("jane.doe@example.com", pessoa.getEmail());
        assertNull(pessoa.getPassword());
    }

    @Test
    public void testCopiarAtributosWithNullValues() {
        // Cria objetos Pessoa para origem e destino
        Pessoa origem = new Pessoa();
        origem.setId("1");
        origem.setName("Jane Doe");
        origem.setPhone(null);
        origem.setEmail("jane.doe@example.com");
        origem.setPassword(null);

        Pessoa destino = new Pessoa();

        // Chama o método que será testado
        pessoasDefinicoes.copiarAtributos(destino, origem);

        // Verifica se os atributos foram copiados corretamente, mesmo com valores nulos
        assertEquals(origem.getName(), destino.getName());
        assertNull(destino.getPhone());
        assertEquals(origem.getEmail(), destino.getEmail());
        assertNull(destino.getPassword());
    }
}
