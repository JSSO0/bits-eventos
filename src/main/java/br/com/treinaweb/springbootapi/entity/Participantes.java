package br.com.treinaweb.springbootapi.entity;

import java.util.Objects;

public class Participantes {
    private String evento_id;
    private String participante_id;
    private String administrador;

    public String getEvento_id() {
        return evento_id;
    }

    public void setEvento_id(String evento_id) {
        this.evento_id = evento_id;
    }

    public String getParticipante_id() {
        return participante_id;
    }

    public void setParticipante_id(String participante_id) {
        this.participante_id = participante_id;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Participantes that = (Participantes) object;
        return Objects.equals(getEvento_id(), that.getEvento_id()) && Objects.equals(getParticipante_id(), that.getParticipante_id()) && Objects.equals(getAdministrador(), that.getAdministrador());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEvento_id(), getParticipante_id(), getAdministrador());
    }
}
