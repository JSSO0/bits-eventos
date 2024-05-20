package br.com.treinaweb.springbootapi.pessoas.controller;

import br.com.treinaweb.springbootapi.general.exceptionhandler.ExcecoesPersonalizadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.treinaweb.springbootapi.pessoas.entity.Pessoa;
import br.com.treinaweb.springbootapi.pessoas.service.PessoaService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/api/pessoas")
@CrossOrigin(origins = "http://localhost:3000")
public class PessoaController {

    @Autowired
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() throws ExcecoesPersonalizadas.ListarExcessao, SQLException {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> obterPessoa(@PathVariable String id) throws ExcecoesPersonalizadas.BuscarPessoaExcessao,SQLException {
        Pessoa pessoa = pessoaService.obterPessoaPorId(id);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) throws ExcecoesPersonalizadas.CriarPessoaExcessao,SQLException {

        pessoa = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable String id, @Valid @RequestBody Pessoa newPessoa) throws ExcecoesPersonalizadas.AtualizarPessoaExcessao,SQLException {
        Pessoa pessoa = pessoaService.atualizarPessoa(id, newPessoa);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirPessoa(@PathVariable String id) throws ExcecoesPersonalizadas.DeletarPessoaExcessao,SQLException {
        pessoaService.excluirPessoa(id);
        return ResponseEntity.noContent().build();
    }

}
