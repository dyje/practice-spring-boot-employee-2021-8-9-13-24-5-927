package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private RetiringCompanyRepository retiringCompanyRepository;
    private Integer companyId;
    @Autowired
    private CompanyRepository companyRepository;
    public CompanyService(RetiringCompanyRepository retiringCompanyRepository) {
        this.retiringCompanyRepository = retiringCompanyRepository;
    }
        private List<Company> companies = new ArrayList<>();

        public List<Employee> getEmployeesByCompany(Integer companyId){
            return companies.stream()
                    .filter(company -> company.getCompanyId().equals(companyId))
                    .findFirst()
                    .get().getEmployees();
        }

}
