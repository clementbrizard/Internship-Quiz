package com.sr03.project.web;

import com.sr03.project.model.Subject;
import com.sr03.project.repository.SubjectRepository;
import com.sr03.project.validator.SubjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManageSubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectValidator subjectValidator;

    @RequestMapping(value = "subjects", method = RequestMethod.GET)
    public String getSubjects(Model model){
        Iterable<Subject> subjectList = subjectRepository.findAll();
        model.addAttribute("subjectList", subjectList);

        return "subjects";
    }
    @RequestMapping(value = "subjects/delete/{id}", method = RequestMethod.POST)
    public String deleteSubjects(Model model, @PathVariable Long id){
        Subject subject = subjectRepository.findById(id);
        subjectRepository.delete(subject);
        return "subjects";
    }
    @RequestMapping(value = "subjects/edit/{id}", method = RequestMethod.GET)
    public String editSubjects(Model model, @PathVariable Long id){
        Subject subject = subjectRepository.findById(id);
        model.addAttribute("subject",subject);
        return "editSubjects";
    }

    @RequestMapping(value = "subjects/edit/{id}", method = RequestMethod.POST)
    public String post_editSubjects(Model model, @PathVariable Long id, @ModelAttribute("subject") Subject subject, BindingResult bindingResult){
        subjectValidator.validate(subject, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/subjects/edit/{id}";
        }
        subjectRepository.save(subject);
        return "redirect:/subjects";
    }

    @RequestMapping(value = "subjects/add", method = RequestMethod.GET)
    public String addSubjects(Model model){
        Subject subject = new Subject();
        model.addAttribute("subject",subject);
        return "addSubjects";
    }

    @RequestMapping(value = "subjects/add", method = RequestMethod.POST)
    public String post_addSubjects(Model model, @ModelAttribute("subject") Subject subject, BindingResult bindingResult){
        subjectValidator.validate(subject, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/subjects/add";
        }

        subjectRepository.save(subject);
        return "redirect:/subjects";
    }
}
