package com.sr03.project.web;

import com.sr03.project.model.*;
import com.sr03.project.service.*;
import com.sr03.project.web.editors.property.CustomTrackQuestionEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class TraineeController {

    @Autowired
    private FormService formService;

    @Autowired
    private FormQuestionService formQuestionService;

    @Autowired
    private UserService userService;

    @Autowired
    private TrackService trackService;

    @Autowired
    private TrackQuestionService trackQuestionService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerQuestionService answerQuestionService;

    // Launch quiz
    @RequestMapping(value = "/forms/{formId}", method = RequestMethod.GET)
    public String launchQuiz(Model model, @PathVariable int formId, Principal principal) {

        // Send first question to display to dedicated method
        Form form = formService.findById(Long.valueOf(formId));
        FormQuestion formQuestion = form.getFormQuestion().iterator().next();
        model.addAttribute("formQuestionPosition", 1);

        // Initiate Track
        Track track = new Track();
        track.setForm(form);
        String name = principal.getName();
        User user = userService.findByUsername(name);
        track.setUser(user);
        trackService.save(track);
        track = trackService.findByFormAndUserAndDate(form, user, track.getDate());
        model.addAttribute("trackId", track.getId().toString());

        return "redirect:/forms/{formId}/questions";
    }

    // Show question
    @RequestMapping(value = "/forms/{formId}/questions", method = RequestMethod.GET)
    public String showQuestion(Model model,
                               @PathVariable int formId,
                               @ModelAttribute("formQuestionPosition") Integer formQuestionPosition,
                               @ModelAttribute("trackId") String trackId) {

        // Get form
        Form form = formService.findById(Long.valueOf(formId));
        model.addAttribute("form", form);

        // Get formQuestion
        FormQuestion formQuestion = formQuestionService.findFormQuestionByFormAndPosition(
                formService.findById(Long.valueOf(formId)),
                formQuestionPosition
        );
        model.addAttribute("formQuestion", formQuestion);

        // Initiate TrackQuestion
        TrackQuestion trackQuestion = new TrackQuestion();
        model.addAttribute("trackQuestion", trackQuestion);

        model.addAttribute("trackId", trackId);

        return "trainee/training";
    }

    // Handle answer
    @RequestMapping(value = "/forms/{formId}/questions/{formQuestionId}/{trackId}", method = RequestMethod.POST)
    public String handleAnswer(Model model,
                                     @PathVariable int formId,
                                     @ModelAttribute("formQuestionId") String formQuestionId,
                               @ModelAttribute("trackId") String trackId,
                               @ModelAttribute("trackQuestion") TrackQuestion trackQuestion) {

        // Save answer to question
        Track track = trackService.findById(Long.valueOf(trackId));
        trackQuestion.setTrack(track);
        FormQuestion formQuestion = formQuestionService.findById(Long.valueOf(formQuestionId));
        trackQuestion.setQuestion(formQuestion.getQuestion());
        trackQuestionService.save(trackQuestion);

        // Update score
        AnswerQuestion answerQuestion = answerQuestionService.findByQuestionAndPosition(formQuestion.getQuestion(), trackQuestion.getChoicePosition());
        if (answerQuestion.getIsValid()) {
            track.setScore(track.getScore() + 1);
            trackService.save(track);
        }

        // Decide whether next question ot back to home
        Form form = formService.findById(Long.valueOf(formId));
        Integer currentPosition = formQuestionService.findById(Long.valueOf(formQuestionId)).getPosition();

        if (formQuestionService.findFormQuestionByFormAndPosition(form, currentPosition + 1) != null) {
            model.addAttribute("formQuestionPosition", currentPosition + 1);
            model.addAttribute("trackId", trackQuestion.getTrack().getId().toString());
            return "redirect:/forms/{formId}/questions";
        }

        else {
            model.addAttribute("score", track.getScore());
            model.addAttribute("user", track.getUser().getId().toString());
            return "redirect:/endQuiz";
        }
    }

    // Redirect to welcome page after quiz
    @RequestMapping(value = "/endQuiz", method = RequestMethod.GET)
    public String quizFinished(Model model,
                               @ModelAttribute("score") String score,
                               @ModelAttribute("user") String userId){

        Iterable <Form> formList = formService.findAll();
        model.addAttribute("formList", formList);
        model.addAttribute("score", score);

        User user = userService.findById(Long.valueOf(userId));
        Iterable<Track> trackList = trackService.findByUser(user);
        model.addAttribute("trackList", trackList);

        return "trainee/trainee";
    }


    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) throws Exception {

        binder.registerCustomEditor(Question.class, new CustomTrackQuestionEditor(trackQuestionService));

    }

}
