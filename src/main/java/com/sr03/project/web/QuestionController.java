package com.sr03.project.web;

import com.sr03.project.model.*;
import com.sr03.project.repository.*;
import com.sr03.project.service.AnswerQuestionService;
import com.sr03.project.service.AnswerService;
import com.sr03.project.service.FormQuestionService;
import com.sr03.project.service.QuestionService;
import com.sr03.project.validator.AnswerQuestionValidator;
import com.sr03.project.validator.AnswerValidator;
import com.sr03.project.validator.FormQuestionValidator;
import com.sr03.project.validator.QuestionValidator;
import com.sr03.project.web.editors.property.CustomAnswerEditor;
import com.sr03.project.web.editors.property.CustomAnswerQuestionEditor;
import com.sr03.project.web.editors.property.CustomFormEditor;
import com.sr03.project.web.editors.property.CustomQuestionEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private FormQuestionRepository formQuestionRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private FormQuestionService formQuestionService;
    @Autowired
    private AnswerService answerService;

    @Autowired
    private AnswerQuestionService answerQuestionService;

    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;

    @Autowired
    private AnswerQuestionValidator answerQuestionValidator;

    @Autowired
    private FormQuestionValidator formQuestionValidator;

    @Autowired
    private QuestionValidator questionValidator;

    @Autowired
    private AnswerValidator answerValidator;

    @RequestMapping(value = "/questions/new", method = RequestMethod.GET)
    public String new_question(Model model,
                               RedirectAttributes redirectAttributes,
                               @ModelAttribute("form_id") final Long form_id) {

        // The form to which we will add a question
        Form currentForm = formRepository.findById(form_id);
        model.addAttribute("form_name", currentForm.getTitle());
        model.addAttribute("form_id", String.valueOf(form_id));
        model.addAttribute("currentForm", currentForm);

        // The existing number of questions of the form
        model.addAttribute("nbQuestions", currentForm.getFormQuestion().spliterator().getExactSizeIfKnown());

        // Lists of existing questions and answers
        Iterable<Question> source = questionRepository.findAll();
        List<Question> questionList = new ArrayList<Question>();
        source.forEach(questionList::add);
        model.addAttribute("questionList", questionList);

        // The new FormQuestion object we will fill
        FormQuestion formQuestionEntity = new FormQuestion();
        formQuestionEntity.setQuestion(new Question());
        model.addAttribute("formQuestionAttribute", formQuestionEntity);

        return "newQuestion";
    }

    @RequestMapping(value = "/questions/new", method = RequestMethod.POST)
    public String new_question(@ModelAttribute("formQuestionAttribute") FormQuestion questionForm,
                               RedirectAttributes redirectAttributes,
                               BindingResult bindingResult,
                               Model model,
                               @ModelAttribute("form_id") Form form) {

        formQuestionValidator.validate(questionForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/questions/new";
        }

        questionForm.setForm(form);
        formQuestionService.save(questionForm);

        String formId = String.valueOf(form.getId());
        redirectAttributes.addFlashAttribute("formId", formId);

        String questionId = String.valueOf(questionForm.getQuestion().getId());
        redirectAttributes.addFlashAttribute("questionId", questionId);

        return "redirect:/questions/new/answers";
    }

    @RequestMapping(value = "/questions/new/answers", method = RequestMethod.GET)
    public String new_answers(Model model,
                              RedirectAttributes redirectAttributes,
                              @ModelAttribute("questionId") Question question,
                              @ModelAttribute("formId") Form form) {

        // The form to which we will add a QuestionAnswer
        String formId = String.valueOf(form.getId());
        redirectAttributes.addFlashAttribute("formId", formId);
        model.addAttribute("formName", form.getTitle());
        model.addAttribute("currentForm", form);

        // The question to which we will add an answer
        model.addAttribute("currentQuestion",question);
        String questionId = String.valueOf(question.getId());
        redirectAttributes.addFlashAttribute("questionId", questionId);

        // The number of answers the question already has
        model.addAttribute("nbAnswers", question.getAnswerQuestion().spliterator().getExactSizeIfKnown());

        // Lists of existing questions, answers and subjects
        Iterable<Question> questionList = questionRepository.findAll();
        model.addAttribute("questionList", questionList);

        Iterable<Answer> answerList = answerRepository.findAll();
        model.addAttribute("answerList", answerList);

        Iterable<Subject> source = subjectRepository.findAll();
        Set<Subject> subjectList = new HashSet<>();
        source.forEach(subjectList::add);
        model.addAttribute("subjectList", subjectList);

        // The new AnswerQuestion object we will fill
        model.addAttribute("answerQuestionForm", new AnswerQuestion());

        return "newAnswers";
    }

    @RequestMapping(value = "/questions/new/answers/{form}", method = RequestMethod.POST)
    public String new_answers(@ModelAttribute("answerQuestionForm") AnswerQuestion answerQuestionForm,
                              RedirectAttributes redirectAttributes,
                              BindingResult bindingResult,
                              @PathVariable("form") String formId) {

        answerQuestionValidator.validate(answerQuestionForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/questions/new/answers";
        }

        answerQuestionService.save(answerQuestionForm);

        redirectAttributes.addFlashAttribute("formId", formId);

        Question question = answerQuestionForm.getQuestion();
        String questionId =  String.valueOf(question.getId());
        redirectAttributes.addFlashAttribute("questionId", questionId);

        return "redirect:/questions/new/answers";
    }

    @RequestMapping(value = "/questions/edit/{id}", method = RequestMethod.GET)
    public String edit_question(Model model, @PathVariable int id) {
        Long lid = Long.valueOf(id);
        Iterable<Question> questionList = questionRepository.findAll();
        Iterable<Answer> answerList = answerRepository.findAll();
        Form currentForm = formRepository.findById(lid);
        model.addAttribute("currentForm", currentForm);
        model.addAttribute("form_name", formRepository.findById(lid).getTitle());
        model.addAttribute("nbQuestions", currentForm.getFormQuestion().spliterator().getExactSizeIfKnown());
        model.addAttribute("questionList", questionList);
        model.addAttribute("answerList", answerList);
        model.addAttribute("questionForm", new FormQuestion());
        Iterable<Subject> source = subjectRepository.findAll();
        Set<Subject> subjectList = new HashSet<>();
        source.forEach(subjectList::add);
        model.addAttribute("subjectList", subjectList);

        // model.addAttribute("answerForm",new Answer());
        return "newQuestion";
    }

    @RequestMapping(value = "/questions/edit/{id}", method = RequestMethod.POST)
    public String edit_question(@ModelAttribute("questionForm") FormQuestion questionForm,
                                RedirectAttributes redirectAttributes,
                                BindingResult bindingResult,
                                Model model,
                                @ModelAttribute("currentForm") Form form) {

        formQuestionValidator.validate(questionForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/questions/new";
        }
        questionForm.setForm(form);
        formQuestionService.save(questionForm);
        //  FormQuestion formQuestion = new FormQuestion(form,questionForm);
        //formQuestionService.save(formQuestion);
        //  answerService.save(answerForm);

        redirectAttributes.addFlashAttribute("form_id", form.getId());
        redirectAttributes.addFlashAttribute("question_id", questionForm.getQuestion().getId());
        return "redirect:/questions/new/answers";
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

        binder.registerCustomEditor(Question.class, new CustomQuestionEditor(questionRepository));

        binder.registerCustomEditor(Form.class, new CustomFormEditor(formRepository));

        binder.registerCustomEditor(Answer.class, new CustomAnswerEditor(answerRepository));

        binder.registerCustomEditor(AnswerQuestion.class, new CustomAnswerQuestionEditor(answerQuestionRepository));

    }
}
