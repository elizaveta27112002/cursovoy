package com.example.cursovoy.models;

import javax.persistence.*;

@Entity
@Table(schema = "spring", name="vakansia")
public class Vakansy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String namevakansy;
    private String company;
    private String salary;
    private String experience;
    private String busyness;
    private String skills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamevakansy() {
        return namevakansy;
    }

    public void setNamevakansy(String namevakansy) {
        this.namevakansy = namevakansy;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getBusyness() {
        return busyness;
    }

    public void setBusyness(String busyness) {
        this.busyness = busyness;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Vakansy(String namevakansy, String company, String salary, String experience, String busyness, String skills) {
        this.namevakansy = namevakansy;
        this.company = company;
        this.salary = salary;
        this.experience = experience;
        this.busyness = busyness;
        this.skills = skills;
    }

    public Vakansy() {
    }

}
