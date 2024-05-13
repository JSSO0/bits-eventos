package br.com.treinaweb.springbootapi.company.entity;

import java.sql.Time;
import java.util.Objects;

public class Company {
    private String id;
    private String name;
    private String description;

 

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
