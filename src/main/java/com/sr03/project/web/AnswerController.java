package com.sr03.project.web;

import com.sr03.project.model.Answer;
import com.sr03.project.model.AnswerQuestion;
import com.sr03.project.model.Form;
import com.sr03.project.model.Question;
import com.sr03.project.repository.AnswerQuestionRepository;
import com.sr03.project.repository.AnswerRepository;
import com.sr03.project.service.AnswerQuestionService;
import com.sr03.project.validator.AnswerQuestionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;

    @Autowired
    private AnswerQuestionService answerQuestionService;

    @Autowired
    private AnswerQuestionValidator answerQuestionValidator;

    @RequestMapping(value = "/answers/disable/{id}", method = RequestMethod.POST)
    public String disable(@PathVariable int id) {
        Long lid = Long.valueOf(id);
        Answer answer = answerRepository.findById(lid);
        answer.setIsActive(!answer.getIsActive());
        answerRepository.save(answer);
        return "redirect:/question/new/answers";
    }

    @RequestMapping(value = "/answers/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable int id, @ModelAttribute("formId") Form form_id,@ModelAttribute("questionId") Question question, RedirectAttributes redirectAttributes) {
        Long lid = Long.valueOf(id);
        AnswerQuestion answerQuestion = answerQuestionRepository.findById(lid);
        answerQuestionRepository.delete(answerQuestion);
        redirectAttributes.addFlashAttribute("formId", form_id);
        redirectAttributes.addFlashAttribute("questionId", question);
        return "redirect:/questions/new/answers";
    }

    // Get edit question view
    @RequestMapping(value = "answers/editPosition/{id}", method = RequestMethod.GET)
    public String getEditPositionAnswer(@PathVariable int id, Model model,@ModelAttribute("formId") Form form_id,@ModelAttribute("questionId") Question question){
        Long lid = Long.valueOf(id);
        AnswerQuestion answerQuestion = answerQuestionRepository.findById(lid);
        model.addAttribute("answer", answerQuestion);

        return "editAnswerQuestion";
    }

    // Save edited question
    @RequestMapping(value = "answers/editPosition/{id}", method = RequestMethod.POST)
    public String editPositionQuestion(@ModelAttribute("answer") AnswerQuestion answerQuestion, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable int id,@ModelAttribute("formId") Form form_id,@ModelAttribute("questionId") Question question) {//RedirectAttributes redirectAttributes

        answerQuestionValidator.validate(answerQuestion, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/answers/editPosition/{id}";
        }

        answerQuestion.setAnswer(answerQuestion.getAnswer());
        answerQuestion.setQuestion(answerQuestion.getQuestion());
        answerQuestionService.save(answerQuestion);


        redirectAttributes.addFlashAttribute("form", form_id);
        redirectAttributes.addFlashAttribute("questionId", question);
        return "redirect:/questions/new/answers";
    }
}
