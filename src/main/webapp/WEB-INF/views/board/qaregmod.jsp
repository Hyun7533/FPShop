<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>${data == null ? 'QnA등록' : 'QnA수정'}</h1>
<form action="/board/${param.board_no == null ? 'qareg' : 'qamod'}" method="post" id="frm">
	<input type="hidden" name="board_no" value="${param.board_no == null ? 0 : param.board_no}">
	<div>제목: <input type="text" name="title" value="${data.title}" required></div>	<!--  required 필수로 채워야 form 으로 날라간다. -->
	<div>내용: <textarea name="ctnt" required>${data.ctnt}</textarea></div>
	<div>
		<input type="submit" value="${data == null ? 'QnA등록' : 'QnA수정'}">
	</div>
</form>

