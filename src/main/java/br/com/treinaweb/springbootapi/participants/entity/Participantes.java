package br.com.treinaweb.springbootapi.participants.entity;

import java.util.Objects;

public class Participantes {
private String id;
private String event_id;
private String participants;
private String adm_of_event;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getAdm_of_event() {
        return adm_of_event;
    }

    public void setAdm_of_event(String adm_of_event) {
        this.adm_of_event = adm_of_event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participantes that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEvent_id(), that.getEvent_id()) && Objects.equals(getParticipants(), that.getParticipants()) && Objects.equals(getAdm_of_event(), that.getAdm_of_event());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEvent_id(), getParticipants(), getAdm_of_event());
    }
}
