package com.sr03.project.web;

import com.sr03.project.model.Answer;
import com.sr03.project.model.Question;
import com.sr03.project.model.Subject;
import com.sr03.project.repository.AnswerRepository;
import com.sr03.project.repository.FormRepository;
import com.sr03.project.repository.QuestionRepository;
import com.sr03.project.repository.SubjectRepository;
import com.sr03.project.service.AnswerService;
import com.sr03.project.service.QuestionService;
import com.sr03.project.validator.AnswerValidator;
import com.sr03.project.validator.QuestionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
@SessionAttributes("form_id")
public class QuestionController {
    @Autowired
    private FormRepository formRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionValidator questionValidator;

    @Autowired
    private AnswerValidator answerValidator;

    @RequestMapping(value = "/questions/edit/{id}", method = RequestMethod.GET)
    public String edit_question(Model model, @PathVariable int id) {
        Long lid = Long.valueOf(id);
        Iterable<Question> questionList = questionRepository.findAll();
        Iterable<Answer> answerList = answerRepository.findAll();
        model.addAttribute("form_name", formRepository.findById(lid));
        model.addAttribute("nbQuestions", questionList.spliterator().getExactSizeIfKnown());
        model.addAttribute("questionList", questionList);
        model.addAttribute("answerList", answerList);

        return "newQuestion";
    }

    @RequestMapping(value = "/questions/new", method = RequestMethod.GET)
    public String new_question(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("form_id") final Long form_id) {//, RedirectAttributes redirectAttributes, @ModelAttribute("form_id") final Long form_id) {
        Iterable<Question> questionList = questionRepository.findAll();
        Iterable<Answer> answerList = answerRepository.findAll();
        model.addAttribute("form_name", formRepository.findById(form_id));
        model.addAttribute("nbQuestions", questionList.spliterator().getExactSizeIfKnown());
        model.addAttribute("questionList", questionList);
        model.addAttribute("answerList", answerList);
        model.addAttribute("questionForm",new Question());
        model.addAttribute("answerForm",new Answer());
        Iterable<Subject> source = subjectRepository.findAll();
        Set<Subject> subjectList = new HashSet<>();
        source.forEach(subjectList::add);

        model.addAttribute("subjectList", subjectList);
        return "newQuestion";
    }

    @RequestMapping(value = "/questions/new", method = RequestMethod.POST)
    public String new_question(@ModelAttribute("questionForm") Question questionForm, @ModelAttribute("answerForm") Answer answerForm, BindingResult bindingResult, Model model) {
        questionValidator.validate(questionForm, bindingResult);
        answerValidator.validate(answerForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/questions/new";
        }

        questionService.save(questionForm);
        answerService.save(answerForm);

        return "redirect:/questions/new";
    }

    @RequestMapping(value = "/questions/disable/{id}", method = RequestMethod.POST)
    public String disable(@PathVariable int id) {
        Long lid = Long.valueOf(id);
        Question question = questionRepository.findById(lid);
        question.setIsActive(!question.getIsActive());
        questionRepository.save(question);
        return "redirect:/newQuestion";
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
