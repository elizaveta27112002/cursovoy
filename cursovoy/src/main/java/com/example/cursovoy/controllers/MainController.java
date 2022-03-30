package com.example.cursovoy.controllers;

import com.example.cursovoy.models.Resume;
import com.example.cursovoy.models.Vakansy;
import com.example.cursovoy.repo.ResumeRepository;
import com.example.cursovoy.repo.VakansyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private VakansyRepository vakansyRepository;
    @Autowired
    private ResumeRepository resumeRepository;

    @GetMapping("/")
    public String home() {

        return "home";
    }

    //для работы с вакансиями
    @GetMapping("/vakansy")
    public String vakansyMain(Model model) {
        Iterable<Vakansy> vakansies = vakansyRepository.findAll();
        model.addAttribute("vakansies", vakansies);
        return "vakansy";
    }
    @GetMapping("/vakansy/add")
    public String vakansyAdd(Model model) {
        return "vakansy-add";
    }

    @PostMapping("/vakansy/add")
    public String vakansyPostAdd(@RequestParam String namevakansy,
                               @RequestParam String company, @RequestParam String salary,
                               @RequestParam String experience, @RequestParam String busyness,
                                 @RequestParam String skills, Model model){
        Vakansy vakansy = new Vakansy (namevakansy, company, salary, experience, busyness,skills);
        vakansyRepository.save(vakansy);
        return "redirect:/vakansy";
    }
    @GetMapping("/vakansy/{id}")
    public String vakansyDetails(@PathVariable(value = "id") long id, Model model) {
        if(!vakansyRepository.existsById(id)){
            return "redirect:/vakansy";
        }
        Optional<Vakansy> vakansy = vakansyRepository.findById(id);
        ArrayList<Vakansy> res = new ArrayList<>();
        vakansy.ifPresent(res::add);
        model.addAttribute("vakansy", res);
        return "vakansy-details";
    }
    @GetMapping("/vakansy/{id}/edit")
    public String vakansyEdit(@PathVariable(value = "id") long id, Model model) {
        if(!vakansyRepository.existsById(id)) {
            return "redirect:/vakansy";
        }
        Optional<Vakansy> vakansy = vakansyRepository.findById(id);
        ArrayList<Vakansy> res = new ArrayList<>();
        vakansy.ifPresent(res::add);
        model.addAttribute("vakansy", res);
        return "vakansy-edit";
    }
    @PostMapping("/vakansy/{id}/edit")
    public String vakansyPostUpdate(@PathVariable(value = "id") long id, @RequestParam String namevakansy,
                                  @RequestParam String company, @RequestParam String salary,
                                  @RequestParam String experience,@RequestParam String busyness,
                                    @RequestParam String skills, Model model){
        Vakansy vakansy = vakansyRepository.findById(id).orElseThrow();
        vakansy.setNamevakansy(namevakansy);
        vakansy.setCompany(company);
        vakansy.setSalary(salary);
        vakansy.setExperience(experience);
        vakansy.setBusyness(busyness);
        vakansy.setSkills(skills);
        vakansyRepository.save(vakansy);
        return "redirect:/vakansy";
    }
    @PostMapping("/vakansy/{id}/remove")
    public String vakansyPostDelete(@PathVariable(value = "id") long id, Model model){
        Vakansy vakansy = vakansyRepository.findById(id).orElseThrow();
        vakansyRepository.delete(vakansy);
        return "redirect:/vakansy";
    }
    @GetMapping("/vakansy/searchByVakansy")
    public String vakansySearchGet(@RequestParam String search, Model model) {
        List<Vakansy> result = vakansyRepository.findVakansiesByNamevakansyIsContaining(search);
        model.addAttribute("vakansies", result);
        return "vakansy";
    }
    @PostMapping("/vakansy/searchByVakansy")
    public String vakansySearch(@RequestParam String search, Model model) {
        List<Vakansy> result = vakansyRepository.findVakansiesByNamevakansyIsContaining(search);
        if (result != null) { model.addAttribute("vakansies", result); }
        return "vakansy";
    }
    //работа с резюме
    @GetMapping("/resume")
    public String resumeMain(Model model) {
        Iterable<Resume> resumes = resumeRepository.findAll();
        model.addAttribute("resumes", resumes);
        return "resume";
    }
    @GetMapping("/resume/add")
    public String resumeAdd(Model model) {
        return "resume-add";
    }

    @PostMapping("/resume/add")
    public String resumePostAdd(@RequestParam String fio,
                                @RequestParam String post, @RequestParam String dessalary,
                                @RequestParam String schedule, @RequestParam String city,
                                @RequestParam String citizenship, @RequestParam String nameinstitution,
                                @RequestParam String speciality, @RequestParam String workexperience,
                                @RequestParam("file") MultipartFile file, Model model) throws IOException {
        Resume resume = new Resume (fio, post, dessalary, schedule, city,citizenship,nameinstitution,
                speciality, workexperience);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            resume.setFilename(resultFilename);
        }
        resumeRepository.save(resume);
        return "redirect:/resume";
    }

    @GetMapping("/resume/{id}")
    public String resumeDetails(@PathVariable(value = "id") long id, Model model) {
        if(!resumeRepository.existsById(id)){
            return "redirect:/resume";
        }
        Optional<Resume> resume = resumeRepository.findById(id);
        ArrayList<Resume> res = new ArrayList<>();
        resume.ifPresent(res::add);
        model.addAttribute("resume", res);
        return "resume-details";
    }
    @GetMapping("/resume/{id}/edit")
    public String resumeEdit(@PathVariable(value = "id") long id, Model model) {
        if(!resumeRepository.existsById(id)) {
            return "redirect:/vakansy";
        }
        Optional<Resume> resume = resumeRepository.findById(id);
        ArrayList<Resume> res = new ArrayList<>();
        resume.ifPresent(res::add);
        model.addAttribute("resume", res);
        return "resume-edit";
    }
    @PostMapping("/resume/{id}/edit")
    public String resumePostUpdate(@PathVariable(value = "id") long id, @RequestParam String fio,
                                   @RequestParam String post, @RequestParam String dessalary,
                                   @RequestParam String schedule, @RequestParam String city,
                                   @RequestParam String citizenship, @RequestParam String nameinstitution,
                                   @RequestParam String speciality, @RequestParam String workexperience,
                                   @RequestParam("file") String filename, Model model) throws IOException {
        Resume resume = resumeRepository.findById(id).orElseThrow();

        resume.setFio(fio);
        resume.setPost(post);
        resume.setDessalary(dessalary);
        resume.setSchedule(schedule);
        resume.setCity(city);
        resume.setCitizenship(citizenship);
        resume.setNameinstitution(nameinstitution);
        resume.setSpeciality(speciality);
        resume.setWorkexperience(workexperience);
        resume.setFilename(filename);
        resumeRepository.save(resume);
        return "redirect:/resume";
    }
    @PostMapping("/resume/{id}/remove")
    public String resumePostDelete(@PathVariable(value = "id") long id, Model model){
        Resume resume = resumeRepository.findById(id).orElseThrow();
        resumeRepository.delete(resume);
        return "redirect:/resume";
    }


}
