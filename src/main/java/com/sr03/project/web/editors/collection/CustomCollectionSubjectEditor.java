package com.sr03.project.web.editors.collection;

import com.sr03.project.model.Subject;
import com.sr03.project.repository.SubjectRepository;
import com.sr03.project.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import java.util.Collection;

public class CustomCollectionSubjectEditor extends CustomCollectionEditor {

    @Autowired
    private SubjectRepository subjectRepository;

    public CustomCollectionSubjectEditor(Class<? extends Collection> collectionType, boolean nullAsEmptyCollection) {
        super(collectionType, nullAsEmptyCollection);
    }

    public Object convertElement(Object element) {
        if (element != null) {
            Integer id = Integer.parseInt(element.toString());
            Long subjectId = Long.valueOf(id);
            Subject subject = subjectRepository.findById(subjectId);
            return subject;
        }

        return null;
    }
}
