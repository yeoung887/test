package com.example.spring.dto;

import lombok.Builder;

import java.util.List;

public class PageResponseDTO<E> {

    private int page;

    private int size;

    private int total;

    //시작 페이지 번호
    private int start;

    //끝페이지 번호
    private int end;

    //이전페이지 존재 여부
    private boolean prev;

    //다음페이지 존재 여부
    private boolean next;

    private List<E> boardList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> boardList, int total){
        if(total <= 0){
            return;
        }
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.boardList = boardList;

        this.end = (int)(Math.ceil(this.page / 10.0)) * 10; //화면에서의 마지막 번호

        this.start = this.end - 9; //화면에서의 시작 번호

        int last = (int)(Math.ceil((total/(double)size)));

        this.end = end > last ? last: end;

        this.prev = this.start > 1;

        this.next = total > this.end * this.size;
    }
}
