package com.example.spring.service;

import com.example.spring.domain.Board;
import com.example.spring.dto.BoardDTO;
import com.example.spring.dto.PageRequestDTO;
import com.example.spring.dto.PageResponseDTO;

import java.util.Optional;

public interface BoardService {
    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}


