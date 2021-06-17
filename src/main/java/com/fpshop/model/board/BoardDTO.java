package com.fpshop.model.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO extends BoardEntity {
	
	private int recordCntPerPage;
	private int sIdx;
	private int page;
	private int searchType;
	private String searchText;
	
}
