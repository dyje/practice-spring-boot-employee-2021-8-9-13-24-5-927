package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Company {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String companyName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public Company(Integer id, String companyName, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employees = employees;
    }
    public Company(){

    }

    public Integer getCompanyId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }
}
