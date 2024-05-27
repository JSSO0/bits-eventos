package br.com.treinaweb.springbootapi.pessoas.service;

import java.util.List;

import br.com.treinaweb.springbootapi.pessoas.atribuicoes.PessoasDefinicoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.springbootapi.pessoas.entity.Pessoa;
import br.com.treinaweb.springbootapi.pessoas.implement.PessoaDAO;

import java.sql.SQLException;
@Service
public class PessoaService {
    final PessoaDAO pessoaDAO;

    @Autowired
    public PessoaService(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public List<Pessoa> listarPessoas() throws SQLException {
        return pessoaDAO.listarTodosOsUsuarios();
    }

    public boolean checarLogin(Pessoa pessoa) throws SQLException{
        return pessoaDAO.checarLogin(pessoa);
    }

    public Pessoa obterPessoaPorId(String id) throws SQLException {
        return pessoaDAO.consultarPessoaPorId(id);

    }

    public Pessoa criarPessoa(Pessoa pessoa) throws SQLException {
        pessoaDAO.criarUsuarioNormal(pessoa);
        return pessoa;
    }

    public Pessoa atualizarPessoa(String id, Pessoa newPessoa) throws SQLException {
        Pessoa pessoaExistente = pessoaDAO.consultarPessoaPorId(id);
        if (pessoaExistente != null) {
            PessoasDefinicoes pessoaMapper = new PessoasDefinicoes();
            pessoaDAO.atualizarPessoaNormal(new Pessoa());
            pessoaMapper.copiarAtributos(pessoaExistente, newPessoa);
            return pessoaExistente;
        } else {
            return null;
        }
    }

    public void excluirPessoa(String id) throws SQLException {
        pessoaDAO.excluirPessoa(id);
    }
}
