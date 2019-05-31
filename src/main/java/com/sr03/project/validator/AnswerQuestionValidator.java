package com.sr03.project.validator;

import com.sr03.project.model.AnswerQuestion;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AnswerQuestionValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AnswerQuestion.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AnswerQuestion answerQuestion = (AnswerQuestion) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "question", "NotEmpty");
    }

}
