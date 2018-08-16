package com.boot.stickershop.repository.custom;

import com.boot.stickershop.domain.Board;
import com.boot.stickershop.domain.Product;
import com.boot.stickershop.domain.QProduct;
import com.boot.stickershop.dto.ProductSearch;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {
    public BoardRepositoryImpl(){
        super(Board.class); // 부모 class에게 entity 클래스를 알려줘야 함
        // 부모 클래스에 default constructor가 없기 때문에 구현해줘야 함
    }

    @Override
    public List<Board> getMainBoardsByDSL() {
        return null;
    }
}
