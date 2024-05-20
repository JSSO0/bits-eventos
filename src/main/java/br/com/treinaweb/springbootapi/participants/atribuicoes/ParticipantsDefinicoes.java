package br.com.treinaweb.springbootapi.participants.atribuicoes;

import br.com.treinaweb.springbootapi.participants.entity.Participantes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantsDefinicoes {
    public Participantes mapResultSetToParticipantes(ResultSet resultSet) throws SQLException {
        Participantes participantes = new Participantes();
        participantes.setId(resultSet.getString("id"));
        participantes.setEvent_id(resultSet.getString("event_id"));
        participantes.setUser_id(resultSet.getString("user_id"));
        participantes.setAdm_of_event(resultSet.getString("adm_of_event"));
        return participantes;
    }

    public void copiarAtributos(Participantes destino, Participantes origem) {
        destino.setEvent_id(origem.getEvent_id());
        destino.setUser_id(origem.getUser_id());
        destino.setAdm_of_event(origem.getAdm_of_event());
    }
}
