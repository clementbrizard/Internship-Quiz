package com.sr03.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FormController {
    @RequestMapping(value = "/forms", method = RequestMethod.GET)
    public String showForms(Model model) {
        //model.addAttribute("formForm", new Form());

        return "forms";
    }
}