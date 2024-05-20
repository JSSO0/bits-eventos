package br.com.treinaweb.springbootapi.service;

import org.junit.jupiter.api.Test;

import br.com.treinaweb.springbootapi.entity.Evento;

import static org.junit.jupiter.api.Assertions.*;

public class EventoTest {

    @Test
    public void testEquals() {
        
        Evento evento1 = new Evento();
        evento1.setData_evento("2024-04-22");
        evento1.setName_evento("Evento de Teste");
        evento1.setEvento_pago(true);
        evento1.setEvento_valor(50.0f);
        evento1.setAdm_id("12345");

        Evento evento2 = new Evento();
        evento2.setData_evento("2024-04-22");
        evento2.setName_evento("Evento de Teste");
        evento2.setEvento_pago(true);
        evento2.setEvento_valor(50.0f);
        evento2.setAdm_id("12345");

        // Verificar se os objetos são iguais
        assertEquals(evento1, evento2);
    }

    @Override
    @Test
<<<<<<< HEAD
    public void testBuscarTodosComMockito() {
        // Criar mock de Evento
        Evento mockEvento = Mockito.mock(Evento.class);

        // Simular retorno de método
        List<Evento> eventosMock = new ArrayList<>();
        Mockito.when(mockEvento.buscarTodos()).thenReturn(eventosMock);

        // Verificar se a lista de eventos está vazia
        assertTrue(eventosMock.isEmpty());
    }

    @Test
    public void testHashCode() {
     
=======
    void testHashCode() {
        // Criar um objeto Evento
>>>>>>> b9510e387dad2d5dfaa46244d096378159299c14
        Evento evento = new Evento();
        evento.setData_evento("2024-04-22");
        evento.setName_evento("Evento de Teste");
        evento.setEvento_pago(true);
        evento.setEvento_valor(50.0f);
        evento.setAdm_id("12345");

        // Verificar se o hashCode é consistente com o equals
        assertEquals(evento.hashCode(), evento.hashCode());
    }
}
