package com.example.spring.repository.search;

import com.example.spring.domain.Board;
import com.example.spring.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board; // Q도에인 객체
        JPQLQuery<Board> query = from(board); // select .. from board
        query.where(board.title.contains("1")); // where title like


        // Querydsl로 Pageable 처리 하기
        // Querydsl의 실행 시 Pageable을 처리하는 방법은 BoardSearchImpl이 상속한 QuerydslRespositorySupport라는 클래스 기능을 이용
        // search1()의 코드 중간에 getQuery

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();
        long count = query.fetchCount();

        return null;
    }

    // searchAll 의 반복문과 제어문을 이용한 처리 가능
    // 검색 조건을 의미하는 types는 제목(t), 내용(c), 작성자(w)로 구성된다고 가정하고, 이를 반영해서 코드 작성
    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if ((types != null && types.length > 0) && keyword != null) { // 검색 조건과 키워드가 있다면,
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            } // end for
            query.where(booleanBuilder);
        } // end if

        // bno > 0
        query.where(board.bno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list =  query.fetch();

        long count = query.fetchCount();

        // PageImpl을 이용한 Page<T> 반환
        // 페이징 처리의 최종 결과는 Page<T> 타입을 반환하는 것이므로,
        // Querydsl에서는 이를 직접 처리해야 하는 불편함 존재
        // Spring Data JPA에서는 이를 처리하기 위해 PageImpl이라는 클래스를 제공, 3개의 파라미터로 Page<T> 생성 가능

        // List<T> : 실제 목록 데이터
        // Pageable : 페이지 관련 정보를 가진 객체
        // long : 전체 개수
        // BoardSearchImpl 클래느 내 searchAll()의 마지막 return null 부분에 반영해서 수정
        return new PageImpl<>(list, pageable, count);
    }
}
