package com.sr03.project.web;

import com.sr03.project.model.Answer;
import com.sr03.project.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepository;

    @RequestMapping(value = "/answers/disable/{id}", method = RequestMethod.POST)
    public String disable(@PathVariable int id) {
        Long lid = Long.valueOf(id);
        Answer answer = answerRepository.findById(lid);
        answer.setIsActive(!answer.getIsActive());
        answerRepository.save(answer);
        return "redirect:/question/new/answers";
    }
}
