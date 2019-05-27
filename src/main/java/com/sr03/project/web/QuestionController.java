package com.sr03.project.web;

import com.sr03.project.model.Question;
import com.sr03.project.repository.FormRepository;
import com.sr03.project.repository.QuestionRepository;
import com.sr03.project.repository.SubjectRepository;
import com.sr03.project.service.QuestionService;
import com.sr03.project.validator.QuestionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes("form_id")
public class QuestionController {
    @Autowired
    private FormRepository formRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionValidator questionValidator;

    @RequestMapping(value = "/questions/new", method = RequestMethod.GET)
    public String new_question(Model model, RedirectAttributes redirectAttributes,@ModelAttribute("form_id") final Long form_id) {
        Iterable<Question> questionList = questionRepository.findAll();
        model.addAttribute("form_name", formRepository.findById(form_id));
        model.addAttribute("nbQuestions", questionList.spliterator().getExactSizeIfKnown());
        return "newQuestion";
    }

    @RequestMapping(value = "/questions/new", method = RequestMethod.POST)
    public String newForm(@ModelAttribute("questionForm") Question questionForm, BindingResult bindingResult, Model model) {
        questionValidator.validate(questionForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "newQuestion";
        }

        questionService.save(questionForm);

        return "redirect:/questions/new";
    }
}
