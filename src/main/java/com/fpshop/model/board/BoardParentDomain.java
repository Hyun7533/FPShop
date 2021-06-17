package com.fpshop.model.board;

import java.util.List;


public class BoardParentDomain {
	private int page;
	private int recordCntPerPage;
	private int maxPageNum;
	private List<BoardDomain> list;
	private int sPage;
	private int ePage;
		
	public int getsPage() {
		return sPage;
	}
	public void setsPage(int sPage) {
		this.sPage = sPage;
	}
	public int getePage() {
		return ePage;
	}
	public void setePage(int ePage) {
		this.ePage = ePage;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecordCntPerPage() {
		return recordCntPerPage;
	}
	public void setRecordCntPerPage(int recordCntPerPage) {
		this.recordCntPerPage = recordCntPerPage;
	}
	public int getMaxPageNum() {
		return maxPageNum;
	}
	public void setMaxPageNum(int maxPageNum) {
		this.maxPageNum = maxPageNum;
	}
	public List<BoardDomain> getList() {
		return list;
	}
	public void setList(List<BoardDomain> list) {
		this.list = list;
	}
}
