package br.com.treinaweb.springbootapi.participants.entity;

import java.util.Objects;

public class Participantes {
private String id;
private String event_id;
private String user_id;
private Boolean adm_of_event;

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Boolean getAdm_of_event() {
        return adm_of_event;
    }

    public void setAdm_of_event(Boolean adm_of_event) {
        this.adm_of_event = adm_of_event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participantes that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEvent_id(), that.getEvent_id()) && Objects.equals(getUser_id(), that.getUser_id()) && Objects.equals(getAdm_of_event(), that.getAdm_of_event());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEvent_id(), getUser_id(), getAdm_of_event());
    }
}
