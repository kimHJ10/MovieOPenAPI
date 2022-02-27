<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
response.setHeader("pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.addHeader("Cache-Control", "no-store");    // 일부파이어폭스버그관련
response.setDateHeader("Expires", 1L);              //응답결과의만료일지정
%>

<!DOCTYPE html>
<html>

<%@ include file="../head.jsp" %>

<body>

	<%@ include file="../navbar.jsp" %>

	<div class="movieList" style="width:1400px; padding:30px; border:1px solid #ddd; border-radius:5px; margin: 20px auto;" >
		<c:if test="${sessionScope.sessionUserId==null || sessionScope.sessionUserId==''}">					
			<h3>로그인하시면 영화정보를 열람 할 수 있습니다</h3>
		</c:if>
		
		<table class="table table-hover">
		<thead class="table-primary">
			<tr class="text-center">
				<td>영화명</td>
				<td>MOVIE NAME</td>
				<td>개봉연도</td>
				<td>제작국가</td>
				<td>감독</td>
			</tr>
			</thead>
			<tbody>
			<c:if test="${not empty result.movieListResult.movieList}">
			<c:forEach items="${result.movieListResult.movieList}" var="movie">
			<tr class="text-center">
				<td>
				<c:if test="${sessionScope.sessionUserId==null || sessionScope.sessionUserId==''}">					
					<c:out value="${movie.movieNm}"/>
				</c:if>
				<c:if test="${sessionScope.sessionUserId !=null && sessionScope.sessionUserId !=''}">
					<a class="movieView" href="<c:out value='${movie.movieCd}'/>">
					<c:out value="${movie.movieNm}"/>
					</a>
				</c:if>
				</td>
				<td><c:out value="${movie.movieNmEn}"/></td>
				<td><c:out value="${movie.openDt}"/></td>
				<td><c:out value="${movie.repNationNm}"/></td>
				<td>
					<c:forEach items="${movie.directors}" var="director">
						<c:out value="${director.peopleNm}"/>
					</c:forEach>
				</td>
			</tr>
			</c:forEach>
			</c:if>
			</tbody>
		</table>
	</div>
	
	<div class="search" style="width:1400px; padding:30px; border:1px solid #ddd; border-radius:5px; margin: 0px auto">
					<form class="searchForm" action="">
						감독명 : <input type="text" name="directorNm" value="${directorNm}">
						영화명 : <input type="text" name="movieNm" value="${movieNm}">
						국적 : <select name="repNationCd">
								<option value="">전체</option>
								<c:forEach items="${nationCd.codes}" var="code">
								<option value="<c:out value="${code.fullCd}"/>"
										<c:if test="${param.repNationCd eq code.fullCd}"> selected="seleted"</c:if>>
										<c:out value="${code.korNm}"/>
								</option>
								</c:forEach>
								</select>
						영화형태 : <c:forEach items="${movieTypeCd.codes}" var="code" varStatus="status">
									<input type="checkbox" name="movieTypeCdArr" value="<c:out value="${code.fullCd}"/>" 
											id="movieTpCd_<c:out value="${code.fullCd}"/>"/>
									<label for="movieTpCd_<c:out value="${code.fullCd}"/>"><c:out value="${code.korNm}"/></label>
									<c:if test="${status.count %4 eq 0}"></c:if>
								</c:forEach>		
						<input type="submit" name="" value="조회">
					</form>
	</div>
	
	<div style="width:1400px; padding:30px; border:0px solid #ddd; border-radius:5px; margin: 0px auto">
	<form action="movieList" method="get" id="moveForm">
			<input type="hidden" name="curPage" value="${curPage}"/>
		</form>
			<ul class="pagination justify-content-center">
				<li class="page-item ${prevPage <= 0 ? 'disabled' : ''}">
				<a class="page-link" href="${prevPage}">◀</a>
				</li>										
				<c:forEach var="i" begin="${blockStart}" end="${blockEnd}">
					<li class="page-item ${curPage == i ? 'active' : ''}">
						<a class="page-link" href="${i}">${i}</a>
					</li>
				</c:forEach>
				<li class="page-item ${blockEnd >= totalPage ? 'disabled' : ''}">
					<a class="page-link" href="${nextPage}">▶</a>
				</li>
			</ul>
	</div>
	
	<%@ include file="../foot.jsp" %>
	
</body>
</html>