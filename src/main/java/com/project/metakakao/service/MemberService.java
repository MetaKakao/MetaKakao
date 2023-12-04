package com.project.metakakao.service;

import com.project.metakakao.dto.MemberJoinDTO;

public interface MemberService {
    static class MidExistException extends Exception {
    }
    void join(MemberJoinDTO memberJoinDTO)throws MidExistException ;

}
