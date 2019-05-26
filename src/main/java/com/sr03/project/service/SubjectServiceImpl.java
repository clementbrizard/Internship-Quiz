package com.sr03.project.service;

import com.sr03.project.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

/*   @Override
    public void save(Form form) {
        form.setActive(true);
        formRepository.save(form);
    }*/
/*    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id);
    }*/
}
