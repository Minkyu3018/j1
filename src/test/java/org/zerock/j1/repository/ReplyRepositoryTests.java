package org.zerock.j1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j1.domain.Board;
import org.zerock.j1.domain.Reply;
import org.zerock.j1.dto.BoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.ReplyDTO;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.service.ReplyService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyService replyService;


    @Test
    public void insertOne(){

        Long bno = 100L;

        Board board = Board.builder().bno(bno).build();

        Reply reply = Reply.builder()
                    .replyText("Reply...1")
                    .replyer("replyer00")
                    .board(board)
                    .build();

        replyRepository.save(reply);
    }

    @Test
    public void testInsertDummies(){

        Long[] bnoArr = {98L, 95L, 93L, 87L, 82L};

        for (Long bno : bnoArr) {

            Board board = Board.builder().bno(bno).build();

            for(int i = 0; i < 50; i++){
                
                Reply reply = Reply.builder()
                            .replyText("Reply..."+bno+"--"+i)
                            .replyer("replyer"+i)
                            .board(board)
                            .build();

                replyRepository.save(reply);
            }

        }//end for
    }

    @Test
    public void testListBoard(){

        Long bno = 99L;

        Pageable pageable = PageRequest.of(0,10,Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.listBoard(bno, pageable);
        
        // 위험한 코드!!!
        result.get().forEach(r -> log.info(r));

    }

    @Test
    public void test0706_1() {

        PageRequestDTO pageRequest = new PageRequestDTO();

        PageResponseDTO<BoardListRcntDTO> responseDTO =
             boardRepository.searchDTORcnt(pageRequest);

        log.info(responseDTO);
    }

    @Test
    public void testCount() {

        Long bno = 98L;

        long count = replyRepository.getCountBoard(bno);

        log.info("count:" + count);
    }

    @Test
    public void testListLast() {

        ReplyPageRequestDTO requestDTO = ReplyPageRequestDTO
        .builder()
        .bno(98L)
        .last(true)
        .build();

        PageResponseDTO<ReplyDTO> a = replyService.list(requestDTO);

        log.info(a);
    }


    
}
