package com.sr03.project.validator;

import com.sr03.project.model.Form;
import com.sr03.project.model.Question;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class QuestionValidator  implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Form.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Question question = (Question) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
    }
}
