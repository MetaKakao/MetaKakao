package com.project.metakakao.dto;

import lombok.*;

@Builder
@Data

public class KakaoDTO {
    private long id;
    private String email;
    private String nickname;
//    private String access Token;
//    private String refreshToken;
}