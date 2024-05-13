package br.com.treinaweb.springbootapi.pessoas.atribuicoes;

import br.com.treinaweb.springbootapi.pessoas.entity.Pessoa;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoasDefinicoes {

    public Pessoa mapResultSetToPessoa(@NotNull ResultSet resultSet) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setName(resultSet.getString("name"));
        pessoa.setCompany_id(resultSet.getString("company_id"));
        pessoa.setPhone(resultSet.getString("phone"));
        pessoa.setEmail(resultSet.getString("email"));
        pessoa.setPassword(resultSet.getString("password"));
        pessoa.setAdm(resultSet.getBoolean("is_adm"));
        return pessoa;
    }

    public void copiarAtributos(@NotNull Pessoa destino, @NotNull Pessoa origem) {
        destino.setName(origem.getName());
        destino.setCompany_id(origem.getCompany_id());
        destino.setPhone(origem.getPhone());
        destino.setEmail(origem.getEmail());
        destino.setPassword(origem.getPassword());
        destino.setAdm(origem.getAdm());
    }
}
