package com.fpshop.model.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardEntity {
	private int board_no;
	private int seq;
	private String title;
	private String ctnt;
	private String registerdate;
	private int hits;
	private int user_pk;
	
}
