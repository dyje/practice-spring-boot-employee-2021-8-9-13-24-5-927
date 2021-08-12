package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    List<Company> companies = new ArrayList<>();
    List<Employee> employees = new ArrayList<>();
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAllCompanies(){
        return companies;
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<Employee> getEmployeesByCompany (@PathVariable Integer companyId) {
        return companyService.getEmployeesByCompany(companyId);
    }
}
