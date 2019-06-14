package com.sr03.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "answer_question")
public class AnswerQuestion implements Comparable<AnswerQuestion>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="answer_id")
    private Answer answer;

    @NotNull
    private Integer position;

    @NotNull
    private Boolean isValid;

    public AnswerQuestion(Integer position, Boolean isValid) {
        this.position = position;
        this.isValid = isValid;
    }

    public AnswerQuestion() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
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

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean valid) {
        isValid = valid;
    }

    @Override
    public int compareTo(AnswerQuestion answerQuestion) {
        if (this.getId().compareTo(answerQuestion.getId()) == 0) {
            return 0;
        }

        else if (this.getPosition() == null)
            if (answerQuestion.getPosition() == null)
                return 0;
            else
                return -1;
        else
        if (answerQuestion.getPosition() == null)
            return 1;
        else return this.getPosition().compareTo(answerQuestion.getPosition());
    }
}
