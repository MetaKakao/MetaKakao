package com.project.metakakao.repository;

import com.project.metakakao.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // 답변 완료 질문 조회
    @Query("select distinct q from Question q where q.host.mid = :hostID and q.status = :status") // left join fetch q.answer은 나중에
    public List<Question> readAllByMid(String hostID, int status);

    @Modifying // update, delete 메소드에는 넣어줘야 함.
    @Query("delete from Question q where q.host.mid =:hostID and q.qno =:qno")
    public void deleteByHostID(String hostID, Long qno);
}
