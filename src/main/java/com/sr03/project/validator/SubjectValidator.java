package com.sr03.project.validator;

import com.sr03.project.model.Subject;
import com.sr03.project.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SubjectValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Subject.class.equals(aClass);
    }

    @Autowired
    private SubjectService subjectService;
    @Override
    public void validate(Object o, Errors errors) {
        Subject subject = (Subject) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
    }
}
