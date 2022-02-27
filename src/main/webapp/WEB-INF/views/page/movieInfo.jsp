<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<%@ include file="../head.jsp" %>

<body>
	
	<%@ include file="../navbar.jsp" %>
	
		
	<div class="movieInfo" style="width:1400px; padding:30px; border:1px solid #ddd; border-radius:5px; margin: 20px auto;">
		<h1>영화 상세정보</h1>
		<table class="table">
			<thead class="table-primary">
				<tr>
					<td colspan="2">${result.movieInfoResult.movieInfo.movieNm}<br>
									${result.movieInfoResult.movieInfo.movieNmEn}</td>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td class="infoTd">장르</td>
					<td>
						<c:forEach items="${result.movieInfoResult.movieInfo.genres}" var="genres">
							<c:out value="${genres.genreNm}"/>
						</c:forEach>
					</td>
				</tr>		

				<tr>
					<td class="infoTd">개봉연도</td>
					<td>${result.movieInfoResult.movieInfo.openDt}</td>
				</tr>		

				<tr>
					<td class="infoTd">관람등급</td>
					<td>
					<c:forEach items="${result.movieInfoResult.movieInfo.audits}" var="audits">
						<c:out value="${audits.watchGradeNm}"/>
					</c:forEach>
					</td>
				</tr>		

				<tr>
					<td class="infoTd">상영시간</td>
					<td>${result.movieInfoResult.movieInfo.showTm}분</td>
				</tr>

				<tr>
					<td class="infoTd">감독</td>
					<td>
						<c:forEach items="${result.movieInfoResult.movieInfo.directors}" var="directors">
							<c:out value="${directors.peopleNm}"/>
						</c:forEach>
					</td>
				</tr>
		
				<tr>
					<td class="infoTd">배우</td>
					<td>
						<c:forEach items="${result.movieInfoResult.movieInfo.actors}" var="actors">
							<c:out value="${actors.peopleNm}"/>
						</c:forEach>
					</td>
				</tr>
				
				<tr>
					<td class="infoTd">제작사</td>
					<td>
						<c:forEach items="${result.movieInfoResult.movieInfo.companys}" var="companys">
							<c:out value="${companys.companyNm}" />
						</c:forEach>
					</td>
				</tr>
			
			</tbody>
					
		</table>
	</div>
	
	<%@ include file="../foot.jsp" %>
		
</body>
</html>