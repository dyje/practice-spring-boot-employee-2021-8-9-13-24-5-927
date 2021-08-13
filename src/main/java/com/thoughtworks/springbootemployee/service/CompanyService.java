package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer companyId){
        return companyRepository.findById(companyId).orElseThrow(()->new CompanyNotFoundException("Company ID not found."));
    }

    public List<Company> getCompanyByPage(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page - 1, pageSize)).getContent();
    }

    public Company addNewCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompanyById(Integer companyId, Company companyUpdate) {
        Company updatedCompany = companyRepository.findById(companyId)
                .map(company -> updateCompanyInfo(company, companyUpdate))
                .get();
        return companyRepository.save(updatedCompany);

    }

    public Company updateCompanyInfo(Company company, Company companyUpdate) {
        if (companyUpdate.getCompanyId() != null) {
            company.setCompanyId(companyUpdate.getCompanyId());
        }
        return company;
    }

    public List<Employee> getAllEmployeesInCompany(Integer companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        return company.getEmployees();
    }
}
