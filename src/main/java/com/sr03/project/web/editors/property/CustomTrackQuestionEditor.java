package com.sr03.project.web.editors.property;

import com.sr03.project.model.TrackQuestion;
import com.sr03.project.service.TrackQuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

public class CustomTrackQuestionEditor extends PropertyEditorSupport {

    @Autowired
    private TrackQuestionService trackQuestionService;

    public CustomTrackQuestionEditor(TrackQuestionService trackQuestionService) {
        this.trackQuestionService = trackQuestionService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if (text != null) {
            Long id = Long.valueOf(text);
            TrackQuestion trackQuestion = trackQuestionService.findById(id);
            setValue(trackQuestion);
        }
    }

}
