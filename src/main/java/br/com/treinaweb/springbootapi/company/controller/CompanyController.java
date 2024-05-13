package br.com.treinaweb.springbootapi.company.controller;

import br.com.treinaweb.springbootapi.company.entity.Company;
import br.com.treinaweb.springbootapi.company.service.CompanyService;
import br.com.treinaweb.springbootapi.general.exceptionhandler.ExcecoesPersonalizadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {
    @Autowired
    private final CompanyService companyService;


    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public ResponseEntity<List<Company>> listarCompany() throws ExcecoesPersonalizadas.ListarExcessao, SQLException {
        List<Company> companys = companyService.listarTodasASCompanys();
        return ResponseEntity.ok(companys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> obterCompany(@PathVariable String id) throws ExcecoesPersonalizadas.BuscarPessoaExcessao,SQLException {
        Company company = companyService.consultarCompanyPorId(id);
        if (company != null) {
            return ResponseEntity.ok(company);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Company> criarCompany(@RequestBody Company company) throws ExcecoesPersonalizadas.CriarPessoaExcessao,SQLException {
        company = companyService.criarCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> atualizarCompanyS(@PathVariable String id, @Valid @RequestBody Company newcompany) throws ExcecoesPersonalizadas.AtualizarPessoaExcessao,SQLException {
        Company company = companyService.atualizarCompany(id, newcompany);
        if (company != null) {
            return ResponseEntity.ok(company);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirCompany(@PathVariable String id) throws ExcecoesPersonalizadas.DeletarPessoaExcessao,SQLException {
        companyService.excluirCompany(id);
        return ResponseEntity.noContent().build();
    }
}
