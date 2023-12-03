package com.project.metakakao.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private Long qno;
    private String content;
    private LocalDateTime regDate;
    private String hostID;
}
