package br.com.treinaweb.springbootapi.atribuicoes;

import br.com.treinaweb.springbootapi.entity.Evento;
import br.com.treinaweb.springbootapi.entity.Pessoa;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class EventoDefinicoes {

    public Evento mapResultSetToEvento(@NotNull ResultSet resultSet) throws SQLException {
        Evento evento = new Evento();
        evento.setData_evento(resultSet.getString("data_evento"));
        evento.setName_evento(resultSet.getString("name_evento"));
        evento.setEvento_pago(resultSet.getBoolean("evento_pago"));
        evento.setEvento_valor(resultSet.getFloat("evento_valor"));
        evento.setAdm_id(resultSet.getString("adm_id"));
        return evento;
    }

    public void copiarAtributos(@NotNull Evento destino, @NotNull Evento origem) {
        destino.setData_evento(origem.getData_evento());
        destino.setName_evento(origem.getName_evento());
        destino.setEvento_pago(origem.getEvento_pago());
        destino.setEvento_valor(origem.getEvento_valor());
        destino.setAdm_id(origem.getAdm_id());
    }
    
}

