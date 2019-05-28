package com.sr03.project.web;

import com.sr03.project.model.Form;
import com.sr03.project.model.Subject;
import com.sr03.project.model.User;
import com.sr03.project.repository.FormRepository;
import com.sr03.project.repository.SubjectRepository;
import com.sr03.project.repository.UserRepository;
import com.sr03.project.service.FormService;
import com.sr03.project.validator.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class FormController {
    @Autowired
    private FormService formService;

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

        Form formEntity = new Form();
        formEntity.setSubjects(new ArrayList<Subject>());
        model.addAttribute("formAttribute", formEntity);

        Iterable<Subject> source = subjectRepository.findAll();
        List<Subject> subjectList = new ArrayList<Subject>();
        source.forEach(subjectList::add);

        model.addAttribute("subjectList", subjectList);
        return "newForm";
    }

    @RequestMapping(value = "/forms/new", method = RequestMethod.POST)
    public String newForm(@ModelAttribute("formAttribute") Form form, BindingResult bindingResult, Model model) {

        formService.save(form);
        return "redirect:/forms";
    }


    @RequestMapping(value = "/forms/disable/{id}", method = RequestMethod.POST)
    public String disable(@PathVariable int id) {
        Long lid = Long.valueOf(id);
        Form form = formRepository.findById(lid);
        form.setIsActive(!form.getIsActive());
        formRepository.save(form);
        return "redirect:/forms";
    }

    @RequestMapping(value = "/forms/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable int id) {
        Long lid = Long.valueOf(id);
        Form form = formRepository.findById(lid);
        formRepository.delete(form);
        return "redirect:/forms";
    }

    @RequestMapping(value = "forms/edit/{id}", method = RequestMethod.GET)
    public String getEditForm(@PathVariable int id, Model model){
        Long lid = Long.valueOf(id);
        Form form = formRepository.findById(lid);
        model.addAttribute("form", form);

        return "editForm";
    }

    @RequestMapping(value = "forms/edit/{id}", method = RequestMethod.POST)
    public String editForm(@ModelAttribute("form") Form form, @PathVariable int id, BindingResult bindingResult, Model model){

        Long lid = Long.valueOf(id);
        Form savedForm = formRepository.findById(lid);
        form.setId(lid);
        form.setIsActive(savedForm.getIsActive());

        formService.save(form);

        return "redirect:/forms";
    }

    @InitBinder
    public void initBinder(WebRequest request, WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(List.class, "subjects", new CustomCollectionEditor(List.class) {
            @Override
            public void setAsText(String id) {
                Long subjectId = Long.valueOf((String)id);
                Subject subject = subjectRepository.findById(subjectId);
                this.setValue(subject);
            }
        });
    }

}