package com.sr03.project.validator;

import com.sr03.project.model.Answer;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AnswerValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Answer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Answer question = (Answer) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
    }
}
