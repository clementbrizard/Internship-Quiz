package com.sr03.project.web;

import com.sr03.project.model.*;
import com.sr03.project.repository.*;
import com.sr03.project.service.AnswerQuestionService;
import com.sr03.project.service.AnswerService;
import com.sr03.project.service.FormQuestionService;
import com.sr03.project.service.QuestionService;
import com.sr03.project.validator.AnswerValidator;
import com.sr03.project.validator.FormQuestionValidator;
import com.sr03.project.validator.QuestionValidator;
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
    private FormQuestionValidator formQuestionValidator;

    @Autowired
    private QuestionValidator questionValidator;

    @Autowired
    private AnswerValidator answerValidator;

    @RequestMapping(value = "/questions/edit/{id}", method = RequestMethod.GET)
    public String edit_question(Model model, @PathVariable int id) {
        Long lid = Long.valueOf(id);
        Iterable<Question> questionList = questionRepository.findAll();
        Iterable<Answer> answerList = answerRepository.findAll();
        Form currentForm = formRepository.findById(lid);
        model.addAttribute("currentForm", currentForm);
        model.addAttribute("form_name", formRepository.findById(lid).getTitle());
        model.addAttribute("nbQuestions", currentForm.formQuestion.spliterator().getExactSizeIfKnown());
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

    @RequestMapping(value = "/questions/new", method = RequestMethod.GET)
    public String new_question(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("form_id") final Long form_id) {//, RedirectAttributes redirectAttributes, @ModelAttribute("form_id") final Long form_id) {
       // Iterable<Question> questionList = questionRepository.findAll();
        Iterable<Answer> answerList = answerRepository.findAll();
        Form currentForm = formRepository.findById(form_id);
        // FormQuestion formQuestions = formQuestionRepository.findFormQuestionByForm(currentForm);
        // Iterable<Question> formQuestionList = formQuestions.getQuestion();
        model.addAttribute("form_name", currentForm.getTitle());
        model.addAttribute("currentForm", currentForm);
        model.addAttribute("nbQuestions", currentForm.formQuestion.spliterator().getExactSizeIfKnown());

        model.addAttribute("answerList", answerList);
     //   model.addAttribute("questionForm", new FormQuestion());
        //   model.addAttribute("answerForm",new AnswerQuestion());
        FormQuestion formQuestionEntity = new FormQuestion();
        formQuestionEntity.setQuestion(new Question());
        model.addAttribute("formQuestionAttribute", formQuestionEntity);

        Iterable<Question> source = questionRepository.findAll();
        List<Question> questionList = new ArrayList<Question>();
        source.forEach(questionList::add);

        model.addAttribute("questionList", questionList);
        return "newQuestion";
    }

    @RequestMapping(value = "/questions/new/answers", method = RequestMethod.GET)
    public String new_answers(Model model, RedirectAttributes redirectAttributes,@ModelAttribute("question_id") final Long question_id, @ModelAttribute("form_id") final Long form_id) {//, RedirectAttributes redirectAttributes, @ModelAttribute("form_id") final Long form_id) {
        Iterable<Question> questionList = questionRepository.findAll();
        Iterable<Answer> answerList = answerRepository.findAll();
        Form currentForm = formRepository.findById(form_id);
        Question currentQuestion = questionRepository.findById(question_id);
        model.addAttribute("form_name", currentForm.getTitle());
        model.addAttribute("currentForm", currentForm);
        model.addAttribute("nbAnswers", currentQuestion.getAnswerQuestion().spliterator().getExactSizeIfKnown());
        model.addAttribute("questionList", questionList);
        model.addAttribute("answerList", answerList);
        model.addAttribute("answerQuestionForm", new AnswerQuestion());
        Iterable<Subject> source = subjectRepository.findAll();
        Set<Subject> subjectList = new HashSet<>();
        source.forEach(subjectList::add);
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("currentQuestion",currentQuestion);
        return "newAnswers";
    }

    @RequestMapping(value = "/questions/new", method = RequestMethod.POST)
    public String new_question(@RequestParam("question") Question question,@ModelAttribute("formQuestionAttribute") FormQuestion questionForm, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model, @ModelAttribute("currentForm") Form form) {
        formQuestionValidator.validate(questionForm, bindingResult);
//        answerValidator.validate(answerForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/questions/new";
        }
        questionForm.setForm(form);
        questionForm.setQuestion(question);
        formQuestionService.save(questionForm);

        //  FormQuestion formQuestion = new FormQuestion(form,questionForm);
        //formQuestionService.save(formQuestion);
        //  answerService.save(answerForm);

        redirectAttributes.addFlashAttribute("form_id", form.getId());
        redirectAttributes.addFlashAttribute("question_id", questionForm.getQuestion().getId());
        return "redirect:/questions/new/answers";
    }

    @RequestMapping(value = "/questions/edit/{id}", method = RequestMethod.POST)
    public String edit_question(@ModelAttribute("questionForm") FormQuestion questionForm, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model, @ModelAttribute("currentForm") Form form) {
        formQuestionValidator.validate(questionForm, bindingResult);
//        answerValidator.validate(answerForm, bindingResult);

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

    @RequestMapping(value = "/questions/new/answers", method = RequestMethod.POST)
    public String new_answers(@ModelAttribute("answerQuestionForm") AnswerQuestion answerQuestionForm, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model, @ModelAttribute("currentForm") Form form,@ModelAttribute("currentQuestion") Question question) {
        formQuestionValidator.validate(answerQuestionForm, bindingResult);
//        answerValidator.validate(answerForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/questions/new/answers";
        }
        answerQuestionForm.setQuestion(question);
        answerQuestionService.save(answerQuestionForm);
        //  FormQuestion formQuestion = new FormQuestion(form,questionForm);
        //formQuestionService.save(formQuestion);
        //  answerService.save(answerForm);

        redirectAttributes.addFlashAttribute("form_id", form.getId());
        redirectAttributes.addFlashAttribute("question_id", question.getId());
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

        // This custom editor converts selected Questions ids to corresponding Questions objects
        binder.registerCustomEditor(String.class, "question", new CustomCollectionEditor(String.class) {
            @Override
            public Object convertElement(Object element) {
                if (element != null) {
                    Integer id = Integer.parseInt(element.toString());
                    Long questionId = Long.valueOf(id);
                    Question question = questionRepository.findById(questionId);
                    return question;
                }

                return null;
            }
        });
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

        // This custom editor converts selected Answers ids to corresponding Answer objects
        binder.registerCustomEditor(Set.class, "answer", new CustomCollectionEditor(Set.class) {
            @Override
            public Object convertElement(Object element) {
                if (element != null) {
                    Integer id = Integer.parseInt(element.toString());
                    Long answerId = Long.valueOf(id);
                    Answer answer = answerRepository.findById(answerId);
                    return answer;
                }

                return null;
            }
        });

    }
}
