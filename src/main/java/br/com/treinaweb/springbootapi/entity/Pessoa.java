package br.com.treinaweb.springbootapi.entity;


import java.util.Objects;
import java.util.UUID;


public class Pessoa {
    private String id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String username;
    private String password;
    private Boolean administrador;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;
        return Objects.equals(getId(), pessoa.getId()) && Objects.equals(getNome(), pessoa.getNome()) && Objects.equals(getTelefone(), pessoa.getTelefone()) && Objects.equals(getEmail(), pessoa.getEmail()) && Objects.equals(getCpf(), pessoa.getCpf()) && Objects.equals(getUsername(), pessoa.getUsername()) && Objects.equals(getPassword(), pessoa.getPassword()) && Objects.equals(getAdministrador(), pessoa.getAdministrador());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getTelefone(), getEmail(), getCpf(), getUsername(), getPassword(), getAdministrador());
    }


}