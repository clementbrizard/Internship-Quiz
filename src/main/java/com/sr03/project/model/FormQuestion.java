package com.sr03.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Comparator;

@Entity
@Table(name = "form_question")
public class FormQuestion implements Comparable<FormQuestion> {

    @Id
    @GeneratedValue
    @Column(name = "form_question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="form_id")
    private Form form;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="question_id")
    private Question question;

    @NotNull
    private Integer position;

    public FormQuestion(Integer position) {
        this.position = position;
    }

    public FormQuestion(Form form,Question question) {
        this.form = form;
        this.question = question;
    }

    public FormQuestion() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    private boolean sameAsFormer(Form newForm) {
        return form==null? newForm == null : form.equals(newForm);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public int compareTo(FormQuestion formQuestion) {
        if (this.getId().compareTo(formQuestion.getId()) == 0) {
            return 0;
        }

        else if (this.getPosition() == null)
            if (formQuestion.getPosition() == null)
                return 0;
            else
                return -1;
            else
                if (formQuestion.getPosition() == null)
                    return 1;
                else return this.getPosition().compareTo(formQuestion.getPosition());
    }

}
