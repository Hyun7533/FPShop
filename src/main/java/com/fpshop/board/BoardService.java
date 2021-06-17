package com.fpshop.board;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.fpshop.model.board.BoardDTO;
import com.fpshop.model.board.BoardDomain;
import com.fpshop.model.board.BoardEntity;
import com.fpshop.model.board.BoardParentDomain;
import com.fpshop.model.Const;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	
	@Autowired
	final BoardMapper mapper;
	
	public int insBoard(BoardEntity p) {
		return mapper.insBoard(p);
	}
	
	public int updBoard(BoardEntity p) {
		return mapper.updBoard(p);
	}
	
	public int delBoard(BoardDTO p) {
		return mapper.delBoard(p);
	}
	
	// 리스트 페이지 처리 함께
	
	public BoardDomain selBoard(BoardDTO p) {
		return mapper.selBoard(p);
	}

}
