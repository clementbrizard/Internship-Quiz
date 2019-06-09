package com.sr03.project.web;

import com.sr03.project.model.Form;
import com.sr03.project.model.FormQuestion;
import com.sr03.project.service.FormQuestionService;
import com.sr03.project.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

@Controller
public class TraineeController {

    @Autowired
    private FormService formService;

    @Autowired
    private FormQuestionService formQuestionService;

    @RequestMapping(value = "/forms/{formId}", method = RequestMethod.GET)
    public String launchQuiz(Model model,
                             @PathVariable int formId) {

        Form form = formService.findById(Long.valueOf(formId));
        FormQuestion formQuestion = form.getFormQuestion().iterator().next();

        model.addAttribute("formQuestionId", String.valueOf(formQuestion.getId()));

        return "redirect:/forms/{formId}/questions";
    }

    // Show question
    @RequestMapping(value = "/forms/{formId}/questions", method = RequestMethod.GET)
    public String showQuestion(Model model,
                               @PathVariable int formId,
                               @ModelAttribute("formQuestionId") String formQuestionId) {

        FormQuestion formQuestion = formQuestionService.findById(Long.valueOf(formQuestionId));

        model.addAttribute("formQuestion", formQuestion);
        model.addAttribute("formId", formId);

        return "trainee/training";
    }

    // Handle answer
    @RequestMapping(value = "/forms/{formId}/questions/{formQuestionId}", method = RequestMethod.POST)
    public String handleAnswer(Model model,
                                     @PathVariable int formId,
                                     @ModelAttribute("formQuestionId") String formQuestionId) {

        FormQuestion formQuestion = formQuestionService.findById(Long.valueOf(formQuestionId));
        System.out.println(formQuestion.getQuestion().getTitle());

        model.addAttribute("formQuestionId", formQuestionId);

        return "redirect:/forms/{formId}/questions";
    }

}
