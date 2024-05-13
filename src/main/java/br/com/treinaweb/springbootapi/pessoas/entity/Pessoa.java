package br.com.treinaweb.springbootapi.pessoas.entity;


import java.util.Objects;
import java.util.UUID;


public class Pessoa {
    private String id;
    private String name;
    private String company_id;
    private String phone;
    private String email;
    private Boolean isAdm;
    private String password;



    public Pessoa() {
        this.id = UUID.randomUUID().toString();
    }

    // Getter e Setter para o novo ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public Boolean getAdm() {
        return isAdm;
    }

    public void setAdm(Boolean adm) {
        isAdm = adm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;
        return Objects.equals(getName(), pessoa.getName()) && Objects.equals(getCompany_id(), pessoa.getCompany_id()) && Objects.equals(getPhone(), pessoa.getPhone()) && Objects.equals(getEmail(), pessoa.getEmail()) && Objects.equals(getPassword(), pessoa.getPassword()) && Objects.equals(isAdm, pessoa.isAdm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCompany_id(), getPhone(), getEmail(), getPassword(), isAdm);
    }
}