package com.project.metakakao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizNo;

    @Column(nullable = false)
    private String title;
    private String q1;
    private int a1;
    private String q2;
    private int a2;
    private String q3;
    private int a3;
    private String q4;
    private int a4;
    private String q5;
    private int a5;
    private String q6;
    private int a6;
    private String q7;
    private int a7;
    private String q8;
    private int a8;
    private String q9;
    private int a9;
    private String q10;
    private int a10;





    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
    @JoinColumn(name = "HOST_ID", updatable = false)
    @JsonBackReference
    private Member host; // 질문 작성자 -> 본인



}
