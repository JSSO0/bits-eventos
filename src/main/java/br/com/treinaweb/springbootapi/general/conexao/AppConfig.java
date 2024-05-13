package br.com.treinaweb.springbootapi.general.conexao;

import br.com.treinaweb.springbootapi.company.implement.CompanyDAO;
import br.com.treinaweb.springbootapi.evento.implement.EventoDAO;
import br.com.treinaweb.springbootapi.participants.implement.ParticipantsDAO;
import br.com.treinaweb.springbootapi.pessoas.implement.PessoaDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/backup_db_bits_events"); // Nome do banco de dados corrigido
        dataSource.setUsername("postgres");
        dataSource.setPassword("1234");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean
    public PessoaDAO pessoaDAO(DataSource dataSource) throws SQLException {
        return new PessoaDAO(dataSource.getConnection());
    }

    @Bean
    public ParticipantsDAO participantsDAO(DataSource dataSource) throws SQLException {
        return new ParticipantsDAO(dataSource.getConnection());
    }

    @Bean
    public CompanyDAO companyDAO(DataSource dataSource) throws SQLException {
        return new CompanyDAO(dataSource.getConnection());
    }

    @Bean
    public EventoDAO eventoDAO(DataSource dataSource) throws SQLException {
        return new EventoDAO(dataSource.getConnection());
    }

}
