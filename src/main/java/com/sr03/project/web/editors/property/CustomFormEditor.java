package com.sr03.project.web.editors.property;

import com.sr03.project.model.Form;
import com.sr03.project.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

public class CustomFormEditor extends PropertyEditorSupport {

    @Autowired
    private FormRepository formRepository;

    public CustomFormEditor(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if (text != null) {
            Long id = Long.valueOf(text);
            System.out.println("DEBUUUUUUUUUUUUUG " + id + " " + id.getClass());
            System.out.println("Form repository " + formRepository);
            Form form = formRepository.findById(id);
            System.out.println(form);
            setValue(form);
        }
    }
}
