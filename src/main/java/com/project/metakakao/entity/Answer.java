package com.project.metakakao.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "question")
public class Answer {

    @Id
    @Column(name = "ANSWER_ID")
    private int ano;

    private String content;

    private LocalDateTime regDate;

    public void changeAnswer(String answer) {
        this.content = answer;
    }
}
