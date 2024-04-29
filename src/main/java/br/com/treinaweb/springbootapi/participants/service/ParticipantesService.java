package br.com.treinaweb.springbootapi.participants.service;

import br.com.treinaweb.springbootapi.participants.atribuicoes.ParticipantsDefinicoes;
import br.com.treinaweb.springbootapi.participants.entity.Participantes;
import br.com.treinaweb.springbootapi.participants.implement.ParticipantsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class ParticipantesService {
    final ParticipantsDAO  participantsDAO;

    @Autowired
    public ParticipantesService(ParticipantsDAO participantsDAO){
        this.participantsDAO = participantsDAO;
    }

    public List<Participantes> listarParticipantes() throws SQLException{
        return participantsDAO.listarTodosParticipantes();
    }

    public Participantes obterParticipantesId(String id) throws SQLException{
        return participantsDAO.consultarParticipante(id);
    }

    public Participantes criarParticipante(Participantes participantes) throws SQLException {
        participantsDAO.criarParticipante(participantes);
        return participantes;
    }

    public Participantes atualizarParticipantes(String id, Participantes newParticipante) throws SQLException{
        Participantes participantesExistente = participantsDAO.consultarParticipante(id);
        if( participantesExistente != null){
            ParticipantsDefinicoes participanteMapper = new ParticipantsDefinicoes();
            participantsDAO.atualizarParticipante(new Participantes());
            participanteMapper.copiarAtributos(participantesExistente, newParticipante);
            return  participantesExistente;

        }else {
            return null;
        }
    }
    public void excluirParticipantes(String id) throws SQLException{
        participantsDAO.deletarParticipante(id);
    }
}
