package com.sr03.project.web;

import com.sr03.project.model.Answer;
import com.sr03.project.model.AnswerQuestion;
import com.sr03.project.model.Form;
import com.sr03.project.model.Question;
import com.sr03.project.repository.AnswerQuestionRepository;
import com.sr03.project.repository.AnswerRepository;
import com.sr03.project.service.AnswerQuestionService;
import com.sr03.project.validator.AnswerQuestionValidator;
import com.sr03.project.validator.AnswerValidator;
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

    @Autowired
    private AnswerValidator answerValidator;

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

    @RequestMapping(value = "answers/manage", method = RequestMethod.GET)
    public String getanswers(Model model){
        Iterable<Answer> answerList = answerRepository.findAll();
        model.addAttribute("answerList", answerList);

        return "answers";
    }
    @RequestMapping(value = "answers/manage/delete/{id}", method = RequestMethod.POST)
    public String deleteanswers(Model model, @PathVariable Long id){
        Answer answer = answerRepository.findById(id);
        answerRepository.delete(answer);
        return "answers";
    }
    @RequestMapping(value = "answers/manage/edit/{id}", method = RequestMethod.GET)
    public String editanswers(Model model, @PathVariable Long id){
        Answer answer = answerRepository.findById(id);
        model.addAttribute("answer",answer);

        return "editAnswers";
    }

    @RequestMapping(value = "answers/manage/edit/{id}", method = RequestMethod.POST)
    public String post_editanswers(Model model, @PathVariable Long id, @ModelAttribute("answer") Answer answer, BindingResult bindingResult){
        answerValidator.validate(answer, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/answers/manage/edit/{id}";
        }
        answerRepository.save(answer);
        return "redirect:/answers/manage";
    }

    @RequestMapping(value = "answers/manage/add", method = RequestMethod.GET)
    public String addanswers(Model model){
        Answer answer = new Answer();
        model.addAttribute("answer",answer);
        return "addAnswers";
    }

    @RequestMapping(value = "answers/manage/add", method = RequestMethod.POST)
    public String post_addanswers(Model model, @ModelAttribute("answer") Answer answer, BindingResult bindingResult){
        answerValidator.validate(answer, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/answers/manage/add";
        }


        answerRepository.save(answer);
        return "redirect:/answers/manage";
    }

    @RequestMapping(value = "answers/manage/disable/{id}", method = RequestMethod.POST)
    public String manage_disable(@PathVariable int id){
        Long lid = Long.valueOf(id);
        Answer answer = answerRepository.findById(lid);
        answer.setIsActive(!answer.getIsActive());
        answerRepository.save(answer);
        return "redirect:/answers/manage";
    }
}
