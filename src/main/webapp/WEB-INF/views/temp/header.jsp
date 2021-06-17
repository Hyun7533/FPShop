<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet">

<!DOCTYPE html>
<div class="inner">
    <div class="top-wrap">
        <div class="float-left">
            <ul>
                <li class="header-list"><a href="">포토리뷰</a></li>
                <li class="header-list"><a href="">타임세일</a></li>
                <li class="header-list"><a href="">브랜드</a></li>
        		<li class="header-list"><a href="/board/qalist">Q&amp;A</a></li>
            </ul>
        </div>
        <!-- isAnonymous -->
        
        <div class="float-right">
            <ul>
            <sec:authorize access="isAuthenticated()">
                <li class="header-list"><a href="${pageContext.servletContext.contextPath}/main/logout">로그아웃</a></li>
                <li class="header-list"><a href="${pageContext.servletContext.contextPath}/main/home">회원정보수정</a></li>
                <li class="header-list"><a><sec:authentication property="name" />님 안녕하세요 ^^</a></li>         
            </sec:authorize>
            
            <sec:authorize access="!isAuthenticated()">
                <li class="header-list"><a href="${pageContext.servletContext.contextPath}/main/login">로그인</a></li>
                <li class="header-list"><a href="${pageContext.servletContext.contextPath}/main/join">회원가입</a></li>
            </sec:authorize>
                <li class="header-list"><a href="">장바구니</a></li>
                <li class="header-list"><a href="">주문조회</a></li>
                <li class="header-list"><a href="">고객센터</a></li>
               <sec:authorize access="hasRole('ROLE_ADMIN')"> <!-- change 관리자 일 경우만  -->
                 <li class="header-list"><a href="${pageContext.servletContext.contextPath}/main/home">관리자모드 입니다.</a></li>
            	</sec:authorize>
            
            </ul>
        </div>
    </div>
</div>
<div class="logo-wrap">
    <div class="middle-wrap">
        <a href="/home" class="title">SHOP</a>
    </div>
</div>
<div class="inner">
    <div class="bottom-wrap">
        <ul>
      	  <sec:authorize access="!isAuthenticated() || hasRole('ROLE_USER')">
            <li class="header-list"><a href="">BEST ITEM</a></li>
            <li class="header-list"><a href="">FASHION</a></li>
            <li class="header-list"><a href="">DIGITAL</a></li>
            <li class="header-list"><a href="">CULTURE</a></li>
            <li class="header-list"><a href="">HEALTH</a></li>
            <li class="header-list"><a href="">INTERIOR</a></li>
            <li class="header-list"><a href="">BEAUTY</a></li>
            <li class="header-list"><a href="">LEISURE</a></li>
            <li class="header-list"><a href="">KIDS</a></li>
            <li class="header-list"><a href="">SALE</a></li>
           </sec:authorize>
            
          <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li class="header-list"><a href="">상품 관리</a></li>
            <li class="header-list"><a href="">Q&amp;A 관리</a></li>
            <li class="header-list"><a href="">회원 관리</a></li>
           </sec:authorize>
        </ul>
    </div>
</div>

<style>


@charset "UTF-8";

* {
    margin: 0;
    padding: 0;
    list-style: none;
    text-decoration: none;
    color: black;
    font-family: 'Source Sans Pro', sans-serif;
}

div {
    display: block;
}

.header-list {
    float: left;
    padding: 0 10px;
    font-size: 12px;
}

.inner > .top-wrap > .float-left > ul > li > a {
    height: 35px;
    line-height: 35px;
}

.inner > .top-wrap > .float-right > ul > li > a {
    height: 35px;
    line-height: 35px;
}

.bottom-wrap > ul > li > a{
    height: 46px;
    line-height: 46px;
}

.inner {
    display: flex;
    justify-content: center;
    height: 100%;
    border-bottom: 1px solid rgb(177, 177, 177);
}

.top-wrap {
    display: flex;
    justify-content: space-between;
    width: 1200px;
    margin: 0 auto;
}

.float-left {
    float: left;
}

.middle-wrap {
    height: 130px;
    width: 1200px;
    margin: 0 auto;
    display: flex;
    justify-content: center;
}

.logo-wrap {
    height: 130px;
    text-align: center;
    vertical-align: middle;
    border-bottom: 1px solid rgb(177, 177, 177);
}

.title {
    font-size: 50px;
    height: 130px;
    line-height: 130px;
}

.bottom-wrap {
    width: 1200px;
}

.bottom-wrap > ul {
    display: flex;
    justify-content: space-between;
}
</style>