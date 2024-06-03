package br.com.treinaweb.springbootapi.evento.atribuicoes;

import br.com.treinaweb.springbootapi.evento.entity.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EventoDefinicoesTest {

    private EventoDefinicoes eventoDefinicoes;

    @BeforeEach
    public void setUp() {
        eventoDefinicoes = new EventoDefinicoes();
    }

    @Test
    public void testMapResultSetToEvento() throws SQLException {
        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.getString("data_evento")).thenReturn("2024-06-01");
        when(resultSetMock.getString("name_evento")).thenReturn("Festival de Música");
        when(resultSetMock.getBoolean("evento_pago")).thenReturn(true);
        when(resultSetMock.getFloat("evento_valor")).thenReturn(150.0f);
        when(resultSetMock.getString("adm_id")).thenReturn("adm123");

        Evento evento = eventoDefinicoes.mapResultSetToEvento(resultSetMock);

        assertEquals("2024-06-01", evento.getStarts_in());
        assertEquals("Festival de Música", evento.getName());
        assertTrue(evento.getPayed_event());
        assertEquals(150.0f, evento.getValue_event());
        assertEquals("adm123", evento.getCompany_id());
    }

    @Test
    public void testCopiarAtributos() {
        Evento origem = new Evento();
        origem.getStarts_in("2024-06-01");
        origem.getName("Festival de Música");
        origem.getPayed_event(true);
        origem.getValue_event(150.0f);
        origem.getCompany_id("adm123");

        Evento destino = new Evento();

        eventoDefinicoes.copiarAtributos(destino, origem);

        assertEquals(origem.getStarts_in(), destino.getStarts_in());
        assertEquals(origem.getName(), destino.getName());
        assertEquals(origem.getPayed_event(), destino.getPayed_event());
        assertEquals(origem.getValue_event(), destino.getValue_event());
        assertEquals(origem.getCompany_id(), destino.getCompany_id());
    }

}