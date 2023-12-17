package com.project.metakakao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mid;
    private String mpw;
    private String email;
    private String nickname;
    private String accessToken;
    private String refreshToken;
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    // 받은 질문. 즉 A가 B에게 질문을 작성하면 그 질문은 A의 db에는 저장되지 않고 B의 db에만 저장됨.
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = Question.class) // 엔티티 상태를 병합(Merge)할 때, 연관된 하위 엔티티도 모두 병합
    @JoinColumn(name = "QUESTION_ID", updatable = false) //
    @JsonBackReference
    private List<Question> questionList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "host") // 엔티티 상태를 병합(Merge)할 때, 연관된 하위 엔티티도 모두 병합
    private List<Quiz> quizList;

    private Long numRejected; // 거절 질문 개수

    public void changePassword(String mpw ){
        this.mpw = mpw;
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void clearRoles() {
        this.roleSet.clear();
    }

    public void addRejected() {
        this.numRejected++;
    }

    public void setAccessToken(String accessToken) {
    }
}
