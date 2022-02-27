<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- Modal -->
<div class="container">
	<div class="modal fade" id="loginModal">
	  <div class="modal-dialog">
	    <div class="modal-content ml-5" style="width:400px">
	    
	      <!-- Modal header -->
	      <div class="modal-header border-0"> 
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      
	      <!-- Modal body -->
	      <form action="${ctx}/login">
	      	<div class="modal-body px-5 pt-0">
	      		<input class="form-control" type="text" placeholder="아이디" id="id" name="id"><br>
	      		<input class="form-control" type="password" placeholder="비밀번호" id="pw" name="pw">
	      	</div>
	      
		  <!-- Modal footer -->
		    <div class="modal-footer border-0 d-flex justify-content-center">
		       <button type="submit" class="btn btn-primary" onclick="return chkLogin()">로그인</button>
		    </div>
	      </form>
	    </div>
	  </div>
	</div>
</div> <!-- .container  -->	
<!-- End of Modal -->
<div class="container">
	<br><br>
	<h3><a href="${ctx}/" style="text-decoration:none">CINEMA</a></h3>
</div>

<nav class="navbar navbar-expand-md navbar-dark bg-dark sticky-top">
<div class="container">
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${ctx}/">HOME</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${ctx}/movieList">MOVIE</a>
      </li>
	<c:if test="${sessionScope.sessionUserId==null || sessionScope.sessionUserId==''}">

	</c:if>
    </ul>
	<c:if test="${sessionScope.sessionUserId !=null && sessionScope.sessionUserId !=''}">
		<div class="d-flex justify-content-end">
			<div class="mr-2" style="color: white; line-height:38px;">${sessionScope.sessionUserName}님 환영합니다!!</div>
			<button type="button" class="btn btn-success" onclick="logout()">LOGOUT</button>
		</div>
	</c:if>	
	<c:if test="${sessionScope.sessionUserId==null || sessionScope.sessionUserId==''}">
		<div class="d-flex justify-content-end mr-2">
			<button type="button" class="btn btn-success" data-toggle="modal" data-target="#loginModal">LOGIN</button>
		</div>
      	<div class="d-flex justify-content-end">
			<a type="button" class="btn btn-info" href="${ctx}/join">JOIN</a>
		</div>
	</c:if>

  </div>
</div>
</nav>