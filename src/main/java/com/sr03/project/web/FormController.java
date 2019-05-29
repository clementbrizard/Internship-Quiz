package com.sr03.project.web;

import com.sr03.project.model.Form;
import com.sr03.project.model.Subject;
import com.sr03.project.repository.FormRepository;
import com.sr03.project.repository.SubjectRepository;
import com.sr03.project.service.FormService;
import com.sr03.project.validator.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // Get list of forms
    @RequestMapping(value = "/forms", method = RequestMethod.GET)
    public String showForms(Model model) {
        Iterable<Form> formList = formRepository.findAll();
        List<Form> form = new ArrayList<>();
        formRepository.findAll().forEach(form::add);
        model.addAttribute("nbForms", formList.spliterator().getExactSizeIfKnown());
        model.addAttribute("formList", formList);
        model.addAttribute("repo",subjectRepository);
        model.addAttribute("listtest",form);
        return "forms";
    }

    // Get new form view
    @RequestMapping(value = "/forms/new", method = RequestMethod.GET)
    public String newForm(Model model) {

        Form formEntity = new Form();
        model.addAttribute("formAttribute", formEntity);

        Iterable<Subject> source = subjectRepository.findAll();
        Set<Subject> subjectList = new HashSet<>();
        source.forEach(subjectList::add);

        model.addAttribute("subjectList", subjectList);
        return "newForm";
    }

    // Create new form
    @RequestMapping(value = "/forms/new", method = RequestMethod.POST)
    public String newForm(@ModelAttribute("formAttribute") Form form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {//RedirectAttributes redirectAttributes

        formValidator.validate(form, bindingResult);

        if (bindingResult.hasErrors()) {
            // if we just put "return 'newForm' the subjects are not injected
            return "redirect:/forms/new";
        }


        formService.save(form);

        redirectAttributes.addFlashAttribute("form_id", form.getId());
        return "redirect:/questions/new";
    }

    // Disable or enable form
    @RequestMapping(value = "/forms/disable/{id}", method = RequestMethod.POST)
    public String disable(@PathVariable int id) {
        Long lid = Long.valueOf(id);
        Form form = formRepository.findById(lid);
        form.setIsActive(!form.getIsActive());
        formRepository.save(form);
        return "redirect:/forms";
    }

    // Delete form
    @RequestMapping(value = "/forms/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable int id) {
        Long lid = Long.valueOf(id);
        Form form = formRepository.findById(lid);
        formRepository.delete(form);
        return "redirect:/forms";
    }

    // Get edit form view
    @RequestMapping(value = "forms/edit/{id}", method = RequestMethod.GET)
    public String getEditForm(@PathVariable int id, Model model){
        Long lid = Long.valueOf(id);
        Form form = formRepository.findById(lid);
        model.addAttribute("form", form);

        return "editForm";
    }

    // Save edited form
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
    public void initBinder(ServletRequestDataBinder binder) throws Exception {

        // This custom editor converts selected Subjects ids to corresponding Subject objects
        binder.registerCustomEditor(Set.class, "subjects", new CustomCollectionEditor(Set.class) {
            @Override
            public Object convertElement(Object element) {
                if (element != null) {
                    Integer id = Integer.parseInt(element.toString());
                    Long subjectId = Long.valueOf(id);
                    Subject subject = subjectRepository.findById(subjectId);
                    return subject;
                }

                return null;
            }
        });
    }

}