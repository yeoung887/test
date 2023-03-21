package com.example.spring.repository.search;

import com.example.spring.domain.Board;
import com.example.spring.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> search1(Pageable pageable);

    // 검색을 위한 메소드 선언과 테스트
    // 검색을 위해서 최소 검색 조건들과 키워드가 필요 => types와 keyword로 칭하고,
    // types은 여러 조건의 조합이 가능하도록 처리하는 메소드 BoardSearch에 추가
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}


