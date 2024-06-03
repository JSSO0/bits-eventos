package br.com.treinaweb.springbootapi.participants.controller;

import br.com.treinaweb.springbootapi.general.exceptionhandler.ExcecoesPersonalizadas;
import br.com.treinaweb.springbootapi.participants.entity.Participantes;
import br.com.treinaweb.springbootapi.participants.service.ParticipantesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParticipantesControllerTest {
    @Mock
    private ParticipantesService participantesService;

    @InjectMocks
    private ParticipantesController participantesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarParticipantes() throws ExcecoesPersonalizadas.ListarExcessao, SQLException {
        Participantes participante1 = new Participantes();
        Participantes participante2 = new Participantes();
        List<Participantes> participantesList = Arrays.asList(participante1, participante2);

        when(participantesService.listarParticipantes()).thenReturn(participantesList);

        ResponseEntity<List<Participantes>> response = participantesController.listarParticipantes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(participantesList, response.getBody());
        verify(participantesService, times(1)).listarParticipantes();
    }

    @Test
    public void testObterParticipantes() throws ExcecoesPersonalizadas.BuscarPessoaExcessao, SQLException {
        Participantes participante = new Participantes();
        String id = "1";

        when(participantesService.obterParticipantesId(id)).thenReturn(participante);

        ResponseEntity<Participantes> response = participantesController.obterParticipantes(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(participante, response.getBody());
        verify(participantesService, times(1)).obterParticipantesId(id);
    }

    @Test
    public void testObterParticipantesNotFound() throws ExcecoesPersonalizadas.BuscarPessoaExcessao, SQLException {
        String id = "1";

        when(participantesService.obterParticipantesId(id)).thenReturn(null);

        ResponseEntity<Participantes> response = participantesController.obterParticipantes(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(participantesService, times(1)).obterParticipantesId(id);
    }

    @Test
    public void testCriarParticipantes() throws ExcecoesPersonalizadas.CriarPessoaExcessao, SQLException {
        Participantes participante = new Participantes();

        when(participantesService.criarParticipante(participante)).thenReturn(participante);

        ResponseEntity<Participantes> response = participantesController.criarParticipantes(participante);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(participante, response.getBody());
        verify(participantesService, times(1)).criarParticipante(participante);
    }

    @Test
    public void testAtualizarParticipantes() throws ExcecoesPersonalizadas.AtualizarPessoaExcessao, SQLException {
        Participantes participante = new Participantes();
        String id = "1";

        when(participantesService.atualizarParticipantes(id, participante)).thenReturn(participante);

        ResponseEntity<Participantes> response = participantesController.atualizarParticipantes(id, participante);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(participante, response.getBody());
        verify(participantesService, times(1)).atualizarParticipantes(id, participante);
    }

    @Test
    public void testAtualizarParticipantesNotFound() throws ExcecoesPersonalizadas.AtualizarPessoaExcessao, SQLException {
        Participantes participante = new Participantes();
        String id = "1";

        when(participantesService.atualizarParticipantes(id, participante)).thenReturn(null);

        ResponseEntity<Participantes> response = participantesController.atualizarParticipantes(id, participante);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(participantesService, times(1)).atualizarParticipantes(id, participante);
    }

    @Test
    public void testExcluirParticipantes() throws ExcecoesPersonalizadas.DeletarPessoaExcessao, SQLException {
        String id = "1";

        doNothing().when(participantesService).excluirParticipantes(id);

        ResponseEntity<Object> response = participantesController.excluirParticipantes(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(participantesService, times(1)).excluirParticipantes(id);
    }

}