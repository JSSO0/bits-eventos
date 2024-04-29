package br.com.treinaweb.springbootapi.evento.atribuicoes;

import br.com.treinaweb.springbootapi.evento.entity.Evento;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventoDefinicoes {

    public Evento mapResultSetToEvento(@NotNull ResultSet resultSet) throws SQLException {
        Evento evento = new Evento();
        evento.setId(resultSet.getString("id"));
        evento.setName(resultSet.getString("name"));
        evento.setDescription(resultSet.getString("description"));
        evento.setCreated_at(resultSet.getString("created_at"));
        evento.setStarts_in(resultSet.getString("starts_in"));
        evento.setEnd_in(resultSet.getString("end_in"));
        evento.setParticipants(resultSet.getString("participants"));
        evento.setPayed_event(resultSet.getBoolean("payed_event"));
        evento.setValue_event(resultSet.getString("value_Event"));
        evento.setCompany_id(resultSet.getString("company_id"));
        return evento;
    }

    public void copiarAtributos(@NotNull Evento destino, @NotNull Evento origem) {
        destino.setName(origem.getName());
        destino.setDescription(origem.getDescription());
        destino.setCreated_at(origem.getCreated_at());
        destino.setEnd_in(origem.getEnd_in());
        destino.setParticipants(origem.getParticipants());
        destino.setPayed_event(origem.getPayed_event());
        destino.setValue_event(origem.getValue_event());
        destino.setCompany_id(origem.getCompany_id());
        destino.setId(origem.getId());
    }
}
