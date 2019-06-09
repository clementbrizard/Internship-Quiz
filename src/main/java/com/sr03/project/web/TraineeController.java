package com.sr03.project.web;

import com.sr03.project.model.Form;
import com.sr03.project.model.FormQuestion;
import com.sr03.project.service.FormQuestionService;
import com.sr03.project.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.SortedSet;

@Controller
public class TraineeController {

    @Autowired
    private FormService formService;

    @Autowired
    private FormQuestionService formQuestionService;

    // Launch quiz
    @RequestMapping(value = "/forms/{formId}", method = RequestMethod.GET)
    public String launchQuiz(Model model,
                             @PathVariable int formId) {

        // Send first question to display to dedicated method
        Form form = formService.findById(Long.valueOf(formId));
        FormQuestion formQuestion = form.getFormQuestion().iterator().next();
        model.addAttribute("formQuestionPosition", 1);

        return "redirect:/forms/{formId}/questions";
    }

    // Show question
    @RequestMapping(value = "/forms/{formId}/questions", method = RequestMethod.GET)
    public String showQuestion(Model model,
                               @PathVariable int formId,
                               @ModelAttribute("formQuestionPosition") Integer formQuestionPosition) {

        // Get form
        Form form = formService.findById(Long.valueOf(formId));

        form.getFormQuestion().forEach(formQuestion -> {
            System.out.println(formQuestion.getPosition().toString());
        });

        // Get formQuestion
        FormQuestion formQuestion = formQuestionService.findFormQuestionByFormAndPosition(
                formService.findById(Long.valueOf(formId)),
                1
        );

        model.addAttribute("form", form);
        model.addAttribute("formQuestion", formQuestion);

        return "trainee/training";
    }

    // Handle answer
    @RequestMapping(value = "/forms/{formId}/questions/{formQuestionId}", method = RequestMethod.POST)
    public String handleAnswer(Model model,
                                     @PathVariable int formId,
                                     @ModelAttribute("formQuestionId") String formQuestionId) {

        Form form = formService.findById(Long.valueOf(formId));
        FormQuestion formQuestion = formQuestionService.findById(Long.valueOf(formQuestionId));
        System.out.println(form.getFormQuestionByPosition(formQuestion.getPosition()).getQuestion().getTitle());

        model.addAttribute("formQuestionId", formQuestionId);

        return "redirect:/forms/{formId}/questions";
    }

}
