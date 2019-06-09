package com.sr03.project.validator;

import com.sr03.project.model.FormQuestion;
import com.sr03.project.service.FormQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FormQuestionService formQuestionService;
    @Override
    public void validate(Object o, Errors errors) {
        FormQuestion question = (FormQuestion) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "NotEmpty");
        if (formQuestionService.findFormQuestionByFormAndPosition(question.getForm(),question.getPosition())  != null) {
            errors.rejectValue("position", "Duplicate.formQuestionAttribute.position");
        }
    }
}
