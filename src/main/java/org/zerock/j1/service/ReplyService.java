package org.zerock.j1.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.ReplyDTO;
import org.zerock.j1.dto.ReplyPageRequestDTO;

@Transactional
public interface ReplyService {

    // 모든 리스트 리턴 타입은 PageResponseDTO
    PageResponseDTO<ReplyDTO> list (ReplyPageRequestDTO requestDTO);

    // 댓글 등록
    Long register(ReplyDTO replyDTO);

    // 댓글 조회
    ReplyDTO read(Long rno);

    // 삭제
    void remove(Long rno);

    // 수정
    void modify(ReplyDTO replyDTO);
        


}
