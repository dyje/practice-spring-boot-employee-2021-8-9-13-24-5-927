package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "/{companyId}")
    public Company getCompanyByID(@PathVariable Integer companyId) {
        return companyService.getCompanyById(companyId);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompanyByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return companyService.getCompanyByPage(page, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addNewCompany(@RequestBody Company company) {
        return companyService.addNewCompany(company);
    }

    @PutMapping(path = "/{companyId}")
    public Company updateCompany(@PathVariable Integer companyId, @RequestBody Company companyUpdate) {
        return companyService.updateCompanyById(companyId, companyUpdate);
    }

    @DeleteMapping(path = "/{companyId}")
    public Company removeCompany(@PathVariable Integer companyId) {
        return companyService.removeCompany(companyId);
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<Employee> getAllEmployeesInCompany(@PathVariable Integer companyId) {
        return companyService.getAllEmployeesInCompany(companyId);

    }

}
