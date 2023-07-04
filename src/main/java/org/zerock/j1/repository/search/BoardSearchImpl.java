package org.zerock.j1.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j1.domain.Board;
import org.zerock.j1.domain.QBoard;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(String searchType, String keyword, Pageable pageable ) {

        // 쿼리 도메인 클래스가 필요함 (클린 등 컴파일 후에 진행해야함!)
        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        if(keyword != null && searchType != null) {

            // tc -> [t,c]
            String[] searchArr = searchType.split("");

            // ( )
            BooleanBuilder searchBuilder = new BooleanBuilder();

            // query.where(board.title.contains("1"));

            for (String type : searchArr) {
                switch(type){
                    case "t" -> searchBuilder.or(board.title.contains(keyword));
                    case "c" -> searchBuilder.or(board.content.contains(keyword));
                    case "w" -> searchBuilder.or(board.writer.contains(keyword));
                }
            }// end for

            query.where(searchBuilder);
        }
        

        

        this.getQuerydsl().applyPagination(pageable, query);

        // 목록 데이터를 가져온다. fetch
        List<Board> list = query.fetch();
        long count = query.fetchCount();

        log.info(list);
        log.info("count" + count);

        
        return new PageImpl<>(list, pageable, count);
    }
    
}
