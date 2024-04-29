package br.com.treinaweb.springbootapi.company.entity;

import java.util.Objects;

public class Company {
    private String id;
    private String name;
    private String description;
    private String created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return Objects.equals(getId(), company.getId()) && Objects.equals(getName(), company.getName()) && Objects.equals(getDescription(), company.getDescription()) && Objects.equals(getCreated_at(), company.getCreated_at());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getCreated_at());
    }
}
