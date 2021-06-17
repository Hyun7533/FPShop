package com.fpshop.board;


import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.fpshop.model.board.BoardDTO;
import com.fpshop.model.board.BoardDomain;
import com.fpshop.model.board.BoardEntity;

@Mapper
public interface BoardMapper {

	int insBoard(BoardEntity p);
	int updBoard(BoardEntity p);
	int delBoard(BoardDTO p);
	
	BoardDomain selBoard(BoardDTO p);
	int selMaxPageNum(BoardDTO p);
	List<BoardDomain> selBoardList(BoardDTO p);
	

}
