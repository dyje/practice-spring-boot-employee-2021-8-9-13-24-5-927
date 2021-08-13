package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
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

    public List<Employee> getAllEmployeesInCompany(Integer companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        return company.getEmployees();
    }
}
