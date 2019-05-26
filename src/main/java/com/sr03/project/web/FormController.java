package com.sr03.project.web;

import com.sr03.project.model.Form;
import com.sr03.project.model.Subject;
import com.sr03.project.repository.FormRepository;
import com.sr03.project.repository.SubjectRepository;
import com.sr03.project.repository.UserRepository;
import com.sr03.project.service.FormService;
import com.sr03.project.validator.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class FormController {
    @Autowired
    private FormService formService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormValidator formValidator;

    @Autowired
    private SubjectRepository subjectRepository;

    @RequestMapping(value = "/forms", method = RequestMethod.GET)
    public String showForms(Model model) {
        // model.addAttribute("formForm", new Form());
        Iterable<Form> formList = formRepository.findAll();
        model.addAttribute("nbForms", formList.spliterator().getExactSizeIfKnown());
        model.addAttribute("formList", formList);
        return "forms";
    }

    @RequestMapping(value = "/forms/new", method = RequestMethod.GET)
    public String newForm(Model model) {
        Iterable<Subject> subjectList = subjectRepository.findAll();
        ArrayList<String> titleList = new ArrayList<>();
        subjectList.forEach(subject -> {
            titleList.add(subject.getText());
        });
        model.addAttribute("formForm", new Form());
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("subjectList", titleList);
        return "newForm";
    }

    @RequestMapping(value = "/forms/new", method = RequestMethod.POST)
    public String newForm(@ModelAttribute("formForm") Form formForm, BindingResult bindingResult, Model model) {
        formValidator.validate(formForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "newForm";
        }

        formService.save(formForm);

        return "redirect:/forms";
    }


    @RequestMapping(value = "/forms/disable/{id}", method = RequestMethod.POST)
    public String disable(@PathVariable int id) {
        Long lid = Long.valueOf(id);
        Form form = formRepository.findById(lid);
        form.setActive(!form.getActive());
        formRepository.save(form);
        return "redirect:/form";
    }


}