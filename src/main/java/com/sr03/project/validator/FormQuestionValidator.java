package com.sr03.project.validator;

import com.sr03.project.model.FormQuestion;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FormQuestionValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return FormQuestion.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FormQuestion question = (FormQuestion) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "NotEmpty");
    }
}
