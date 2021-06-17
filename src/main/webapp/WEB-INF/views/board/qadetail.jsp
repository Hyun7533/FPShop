<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div>
	<span class="pointer" onclick="back()">돌아가기</span>
	
		<button onclick="clkDel(${requestScope.data.board_no});">삭제</button>
		<a href="/board/mod?&board_no=${requestScope.data.board_no}">
			<button>수정</button>
		</a>
	<div>
		<div>번호 : ${requestScope.data.seq}</div>
		<div>조회수 : ${requestScope.data.hits}</div>
		<div>
			작성자 :
			<span class="profile-td-nm">
				<c:choose>
					<c:when test="${param.searchType == 4 && param.searchText != ''}">
						${fn:replace(data.writer_nm, param.searchText, '<mark>' += param.searchText += '</mark>')}
					</c:when>
					<c:otherwise>
						${data.writer_nm}
					</c:otherwise>
				</c:choose>
			</span>
		</div>
		<div>제목 : 
		<!-- 검색 내용 기본 노란색 표현 -->
			<c:choose>	
				<c:when test="${(param.searchType == 1 || param.searchType == 3) && param.searchText != ''}">
					${fn:replace(data.title, param.searchText, '<mark>' += param.searchText += '</mark>')}
				</c:when>
				<c:otherwise>
					${data.title}
				</c:otherwise>
			</c:choose>
		</div>
		<div>작성일 : ${data.registerdate}</div>
		<div>
			<c:choose>
				<c:when test="${(param.searchType == 2 || param.searchType == 3) && param.searchText != ''}">
					${fn:replace(data.ctnt, param.searchText, '<mark>' += param.searchText += '</mark>')}
				</c:when>
				<c:otherwise>
					${data.ctnt}
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>