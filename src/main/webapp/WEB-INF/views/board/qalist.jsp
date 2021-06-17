<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="data">
	<input type="hidden" name="searchType" value="0">
	<input type="hidden" name="searchText">
	<input type="hidden" name="page" value="1">


	<sec:authorize access="hasRole('ROLE_USER')"> <!-- change 사용자 일 경우만  -->
          <div>	
			<a href="/board/qaregmod?">
				<button>글쓰기</button>
			</a>
		</div>
   	</sec:authorize>
		
	<div>
		<span>
			<select id="searchType">
				<option value="1" ${param.searchType == 1 ? 'selected' : ''}>제목</option>
				<option value="2" ${param.searchType == 2 ? 'selected' : ''}>내용</option>
				<option value="3" ${param.searchType == 3 ? 'selected' : ''}>제목+내용</option>
				<option value="4" ${param.searchType == 4 ? 'selected' : ''}>작성자</option>
			</select>
			<input type="search" id="searchText" value="${param.searchText}" onkeyup="doSearch(event)">
			<input type="button" value="검색" onclick="getBoardList()">
		</span>
		<form id="listFrm" action="/board/qalist" method="get">
			<input type="hidden" name="searchType" value="0">
			<input type="hidden" name="searchText">
			<input type="hidden" name="page" value="1">
			<select name="recordCntPerPage" onchange="getBoardList()">
				<c:forEach begin="5" end="50" step="5" var="p">
					<option value="${p}" ${requestScope.data.recordCntPerPage == pageScope.p ? 'selected' : ''}>${p}개</option>	
				</c:forEach>
			</select>
		</form>
	</div>
	<c:choose>
		<c:when test="${fn:length(requestScope.data.list) == 0}">
			<div>글이 없습니다.</div>
		</c:when>
		<c:otherwise>
		
			<table>
			<tr>
				<td>번호</td>
				<td class="w200">제목</td>
				<td>조회수</td>
				<td>작성일</td>
				<td>작성자</td>
				<td>board_no</td>
			</tr>	
			<!-- , ${param.searchType}, '${param.searchText}' -->
			<c:forEach items="${requestScope.data.list}" var="item">
				<tr class="pointer" onclick="clkArticle(${item.board_no}, ${param.searchType}, '${param.searchText}')">
					<td>${item.seq}</td>
					<td class="td-ellipsis">
						<c:choose>
							<c:when test="${(param.searchType == 1 || param.searchType == 3) && param.searchText != ''}">
								${fn:replace(item.title, param.searchText, '<mark>' += param.searchText += '</mark>')}
							</c:when>
							<c:otherwise>
								${item.title}
							</c:otherwise>
						</c:choose>
					</td>
					<td>${item.hits}</td>
					<td>${item.registerdate}</td>
					<td class="profile-td">
						<span class="profile-td-nm">
							<c:choose>
								<c:when test="${param.searchType == 4 && param.searchText != ''}">
									${fn:replace(item.writer_nm, param.searchText, '<mark>' += param.searchText += '</mark>')}
								</c:when>
								<c:otherwise>
									${item.writer_nm}
								</c:otherwise>
							</c:choose>						
						</span>
					</td>
					<td>${item.board_no}</td>
				</tr>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
	
	<div class="pageContainer">
		<c:if test="${requestScope.data.sPage > 1}">
			<span class="page" onclick="getBoardList(1)">1</span>
			<span>...</span>
		</c:if>	
		
		<c:forEach begin="${requestScope.data.sPage}" end="${requestScope.data.ePage}" var="i">			
			<span class="page ${requestScope.data.page == i ? 'selected' : ''}" onclick="getBoardList(${i})">${i}</span>
		</c:forEach>
		
		<c:if test="${requestScope.data.ePage < requestScope.data.maxPageNum}">
			<span>...</span>
			<span class="page" onclick="getBoardList(${requestScope.data.maxPageNum})">${requestScope.data.maxPageNum}</span>
		</c:if>
	</div>
</div>  

<script>
// 전역 변수 설정
var dataElem = document.querySelector('#data')
var listFrmElem = document.querySelector('#listFrm')
var searchTypeElem = document.querySelector('#searchType')
var searchTextElem = document.querySelector('#searchText')

//, searchType, searchText
//+'&searchType='+searchType+'&searchText='+searchText

// 글 제목 클릭
function clkArticle(board_no) {		
	var url = '/board/qadetail?board_no='+board_no;
	location.href = url; //주소값 이동
}

// 검색 텍스트에서 엔터치면 검색되게 처리
function doSearch(e) {
	if(e.keyCode === 13) {
		getBoardList()
	}
}

function getBoardList(page) {
	
	var searchTextValue = searchTextElem.value
	if(searchTextValue !== '') {
		var searchTypeValue = searchTypeElem.value
		
		listFrmElem.searchType.value = searchTypeValue
		listFrmElem.searchText.value = searchTextValue
	}
	if(page) {
		listFrmElem.page.value = page
	}
	
	listFrmElem.submit()
}
</script>





<style>
.pointer {
	cursor: pointer;
}

.profile-td {
	display: flex;
    align-items: center;
}

.profile-td .profile-td-nm {
	display: inline-block;
    margin-left: 10px;
}


tr.pointer:hover {
	background-color: #d2dae255;
}
#data {
margin :10px 10px 0 10px
}

</style>

</html>