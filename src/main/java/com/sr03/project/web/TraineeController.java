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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedSet;

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
        trackQuestion.setQuestion(formQuestion.getQuestion());
        trackQuestion.setTrack(trackService.findById(Long.valueOf(trackId)));
        trackQuestionService.save(trackQuestion);
        model.addAttribute("trackQuestion", trackQuestion);

        return "trainee/training";
    }

    // Handle answer
    @RequestMapping(value = "/forms/{formId}/questions/{formQuestionId}", method = RequestMethod.POST)
    public String handleAnswer(Model model,
                                     @PathVariable int formId,
                                     @ModelAttribute("formQuestionId") String formQuestionId,
                               @ModelAttribute("trackQuestion") TrackQuestion trackQuestion) {

        Form form = formService.findById(Long.valueOf(formId));
        Integer currentPosition = formQuestionService.findById(Long.valueOf(formQuestionId)).getPosition();
        if (formQuestionService.findFormQuestionByFormAndPosition(form, currentPosition + 1) != null) {
            model.addAttribute("formQuestionPosition", currentPosition + 1);
            return "redirect:/forms/{formId}/questions";
        }

        else
            return "redirect:/welcome";
    }


    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) throws Exception {

        binder.registerCustomEditor(Question.class, new CustomTrackQuestionEditor(trackQuestionService));

    }

}
