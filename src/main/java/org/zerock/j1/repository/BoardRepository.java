package org.zerock.j1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j1.domain.Board;
import org.zerock.j1.dto.BoardReadDTO;
import org.zerock.j1.repository.search.BoardSearch;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch{

    @Query("select b from Board b where b.bno = :bno")  // <<< 결과물을
    BoardReadDTO readOne(@Param("bno") Long bno);  // <<< 타임으로 바꿔줌



    // step1
    List<Board> findByTitleContaining(String title);

    // ver.1
    @Query("select b from Board b where b.title like %:title%")
    List<Board> listTitle(@Param("title") String title);

    // 원하는 것만 가지고온다  ver.2
    @Query("select b.bno, b.title from Board b where b.title like %:title%")
    List<Object[]> listTitle2(@Param("title") String title);

    // ver.3
    @Query("select b.bno, b.title from Board b where b.title like %:title%")
    Page<Object[]> listTitle2(@Param("title") String title, Pageable pageable);

    // update의 결과물
    @Modifying
    @Query("update Board b set b.title = :title where b.bno = :bno")
    int modifyTitle(@Param("title") String title, @Param("bno") Long bno);
    
    // step2
    Page<Board> findByContentContaining(String content, Pageable pageable);

    // native query, Data Base의 종속적인 쿼리가 되어버림
    // 이거를 쓰는 것 보다는 Mabatis와 결합해서 사용하는 것이 차라리 낫다.
    @Query(value = "select * from t_board", nativeQuery = true)
    List<Object[]> listNative();


    @Query("select b.bno, b.title, b.writer, count(r) from Board b left outer join Reply r on r.board = b group by b order by b.bno desc")
    List<Object[]> getListWithRcnt();



    
}
