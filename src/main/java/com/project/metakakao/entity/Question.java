package com.project.metakakao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qno;

    @Column(nullable = false)
    private String content;

    private LocalDateTime regDate;

//    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
//    @JoinColumn(name = "USER_ID", updatable = false)
//    @JsonBackReference
//    private Member writer; // 작성자

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
    @JoinColumn(name = "HOST_ID", updatable = false)
    @JsonBackReference
    private Member host; // 질문 받는 사람

    @OneToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer; // 외래키가 주 테이블에 있는 단방향 연관관계

    private int status; // 답변 완료: 1, 미응답: 0

}
