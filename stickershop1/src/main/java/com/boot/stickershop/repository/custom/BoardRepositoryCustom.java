package com.boot.stickershop.repository.custom;

import com.boot.stickershop.domain.Board;
import com.boot.stickershop.dto.ProductSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {
    // dynamic JPQL 메서드 선언
    List<Board> getMainBoardsByDSL();
}