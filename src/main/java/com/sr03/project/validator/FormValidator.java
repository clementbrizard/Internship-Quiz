package com.sr03.project.validator;

import com.sr03.project.model.Form;
import com.sr03.project.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Form form = (Form) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
    }
}
