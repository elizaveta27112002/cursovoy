package com.example.cursovoy.models;

import javax.persistence.*;

@Entity
@Table(schema = "spring", name="resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fio;
    private String post;
    private String dessalary;
    private String schedule;
    private String city;
    private String citizenship;
    private String nameinstitution;
    private String speciality;
    private String workexperience;
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDessalary() {
        return dessalary;
    }

    public void setDessalary(String dessalary) {
        this.dessalary = dessalary;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getNameinstitution() {
        return nameinstitution;
    }

    public void setNameinstitution(String nameinstitution) {
        this.nameinstitution = nameinstitution;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getWorkexperience() {
        return workexperience;
    }

    public void setWorkexperience(String workexperience) {
        this.workexperience = workexperience;
    }
    public Resume(String fio, String post, String dessalary, String schedule, String city, String citizenship,
                  String nameinstitution, String speciality, String workexperience) {
        this.fio = fio;
        this.post = post;
        this.dessalary = dessalary;
        this.schedule = schedule;
        this.city = city;
        this.citizenship = citizenship;
        this.nameinstitution = nameinstitution;
        this.speciality = speciality;
        this.workexperience = workexperience;
        //this.filename = filename;
    }
    public Resume() {
    }
}
