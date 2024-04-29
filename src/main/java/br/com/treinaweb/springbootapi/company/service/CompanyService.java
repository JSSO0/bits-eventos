package br.com.treinaweb.springbootapi.company.service;

import br.com.treinaweb.springbootapi.company.atribuicoes.CompanyDefinicoes;
import br.com.treinaweb.springbootapi.company.entity.Company;
import br.com.treinaweb.springbootapi.company.implement.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CompanyService {
    final CompanyDAO companyDAO;

    @Autowired
    public CompanyService(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    public List<Company> listarTodasASCompanys() throws SQLException {
        return companyDAO.listarTodasAsCompanys();
    }

    public Company consultarCompanyPorId(String id) throws SQLException {
        return companyDAO.consultarEmpresa(id);
    }

    public Company criarCompany(Company company) throws SQLException {
        companyDAO.criarEmpresa(company);
        return company;
    }

    public Company atualizarCompany(String id, Company newCompany) throws SQLException {
        Company companyexistente = companyDAO.consultarEmpresa(id);
        if (companyexistente != null) {
            CompanyDefinicoes companyMapper = new CompanyDefinicoes();
            companyDAO.atualizarEmpresa(new Company());
            companyMapper.copiarAtributos(companyexistente, newCompany);
            return companyexistente;
        } else {
            return null;
        }
    }

    public void excluirCompany(String id) throws SQLException {
        companyDAO.deletarEmpresa(id);
    }

}
