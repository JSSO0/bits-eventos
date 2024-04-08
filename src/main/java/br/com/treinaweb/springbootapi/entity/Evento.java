package br.com.treinaweb.springbootapi.entity;

import java.util.Objects;

public class Evento {
    private String data_evento;
    private String name_evento;
    private Boolean evento_pago;
    private Float evento_valor;

    private String adm_id;

    public String getData_evento() {
        return data_evento;
    }

    public void setData_evento(String data_evento) {
        this.data_evento = data_evento;
    }

    public String getName_evento() {
        return name_evento;
    }

    public void setName_evento(String name_evento) {
        this.name_evento = name_evento;
    }

    public Boolean getEvento_pago() {
        return evento_pago;
    }

    public void setEvento_pago(Boolean evento_pago) {
        this.evento_pago = evento_pago;
    }

    public Float getEvento_valor() {
        return evento_valor;
    }

    public void setEvento_valor(Float evento_valor) {
        this.evento_valor = evento_valor;
    }

    public String getAdm_id() {
        return adm_id;
    }

    public void setAdm_id(String adm_id) {
        this.adm_id = adm_id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Evento evento = (Evento) object;
        return Objects.equals(getData_evento(), evento.getData_evento()) && Objects.equals(getName_evento(), evento.getName_evento()) && Objects.equals(getEvento_pago(), evento.getEvento_pago()) && Objects.equals(getEvento_valor(), evento.getEvento_valor()) && Objects.equals(getAdm_id(), evento.getAdm_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData_evento(), getName_evento(), getEvento_pago(), getEvento_valor(), getAdm_id());
    }
}
