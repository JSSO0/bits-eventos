package br.com.treinaweb.springbootapi.atribuicoes;

import br.com.treinaweb.springbootapi.evento.entity.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventoDefinicoesTest {

    private EventoDefinicoes eventoDefinicoes;
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() {
        eventoDefinicoes = new EventoDefinicoes();
        resultSet = mock(ResultSet.class);
    }

    @Test
    public void testMapResultSetToEvento() throws SQLException {
        // Configuração dos dados simulados do ResultSet
        when(resultSet.getString("starts_in")).thenReturn("2024-06-01");
        when(resultSet.getString("name")).thenReturn("Event Name");
        when(resultSet.getBoolean("payed_event")).thenReturn(true);
        when(resultSet.getString("value_event")).thenReturn("100.0");
        when(resultSet.getString("company_id")).thenReturn("company123");

        // Chama o método que será testado
        Evento evento = eventoDefinicoes.mapResultSetToEvento(resultSet);

        // Verifica se os atributos foram mapeados corretamente
        assertEquals("2024-06-01", evento.getStarts_in());
        assertEquals("Event Name", evento.getName());
        assertTrue(evento.getPayed_event());
        assertEquals("100.0", evento.getValue_event());
        assertEquals("company123", evento.getCompany_id());
    }

    @Test
    public void testCopiarAtributos() {
        // Cria objetos Evento para origem e destino
        Evento origem = new Evento();
        origem.setStarts_in("2024-06-01");
        origem.setName("Event Name");
        origem.setPayed_event(true);
        origem.setValue_event("100.0");
        origem.setCompany_id("company123");

        Evento destino = new Evento();

        // Chama o método que será testado
        eventoDefinicoes.copiarAtributos(destino, origem);

        // Verifica se os atributos foram copiados corretamente
        assertEquals(origem.getStarts_in(), destino.getStarts_in());
        assertEquals(origem.getName(), destino.getName());
        assertTrue(destino.getPayed_event());
        assertEquals(origem.getValue_event(), destino.getValue_event());
        assertEquals(origem.getCompany_id(), destino.getCompany_id());
    }
}
