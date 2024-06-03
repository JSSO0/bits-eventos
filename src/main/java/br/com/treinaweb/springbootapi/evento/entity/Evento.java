package br.com.treinaweb.springbootapi.evento.entity;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class Evento {
    private UUID id;
    private String name;
    private String description;
    private String created_at;
    private String starts_in;
    private String end_in;
    private Boolean payed_event;
    private String value_event;
    private String company_id;

    public Integer getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(Integer participantCount) {
        this.participantCount = participantCount;
    }

    private Integer participantCount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStarts_in() {
        return starts_in;
    }

    public void setStarts_in(String starts_in) {
        this.starts_in = starts_in;
    }

    public String getEnd_in() {
        return end_in;
    }

    public void setEnd_in(String end_in) {
        this.end_in = end_in;
    }

    public Boolean getPayed_event() {
        return payed_event;
    }

    public void setPayed_event(Boolean payed_event) {
        this.payed_event = payed_event;
    }

    public String getValue_event() {
        return value_event;
    }

    public void setValue_event(String value_event) {
        this.value_event = value_event;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento evento)) return false;
        return Objects.equals(getId(), evento.getId()) && Objects.equals(getName(), evento.getName()) && Objects.equals(getDescription(), evento.getDescription()) && Objects.equals(getCreated_at(), evento.getCreated_at()) && Objects.equals(getStarts_in(), evento.getStarts_in()) && Objects.equals(getEnd_in(), evento.getEnd_in())&& Objects.equals(getPayed_event(), evento.getPayed_event()) && Objects.equals(getValue_event(), evento.getValue_event()) && Objects.equals(getCompany_id(), evento.getCompany_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getCreated_at(), getStarts_in(), getEnd_in(), getPayed_event(), getValue_event(), getCompany_id());
    }


}
