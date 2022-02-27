<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<%@ include file="../head.jsp" %>

<body>
	<%@ include file="../navbar.jsp" %>
	
	<div class="boxofficeMain" style="width:1400px; padding:30px; border:1px solid #ddd; border-radius:5px; margin: 50px auto">
		<h1>${targetDt} BOXOFFICE</h1>
		<table class="table">
			<thead class="table-primary">
				<tr class="text-center">
					<td></td>
					<td>순위</td>
					<td>영화</td>
					<td>누적 관객수</td>
					<td>관객 증감 비율</td>
					<td>누적 매출액</td>
					<td>매출 증감 비율</td>
				</tr>
			</thead>
			<tbody>
			<c:if test="${not empty dailyResult.boxOfficeResult.dailyBoxOfficeList}">
			<c:forEach items="${dailyResult.boxOfficeResult.dailyBoxOfficeList}" var="boxoffice">
				<tr class="text-center">
					<td><c:out value="${boxoffice.rankInten}"/></td>
					<td><c:out value="${boxoffice.rank}"/></td>
					<td><c:out value="${boxoffice.movieNm}"/></td>
					<td><c:out value="${boxoffice.audiAcc}"/>명</td>
					<td><c:out value="${boxoffice.audiChange}"/>%</td>
					<td><c:out value="${boxoffice.salesAcc}"/>원</td>
					<td><c:out value="${boxoffice.salesChange}"/>%</td>
				</tr>
			</c:forEach>
			</c:if>
			</table>
			</div>
			<div style="width:1400px; padding:30px; border:1px solid #ddd; border-radius:5px; margin: 0px auto">
					<form class="boxForm" action="">
						날짜 : <input type="text" name="targetDt" value="${targetDt}" placeholder="예)20220101">
						영화구분 : <select name="multiMovieYn">
									<option value="">전체</option>
									<option value="Y"<c:if test="${param.multiMovieYn eq 'Y'}"> selected="seleted"</c:if>>다양성영화</option>
									<option value="N"<c:if test="${param.multiMovieYn eq 'N'}"> selected="seleted"</c:if>>상업영화</option>
								</select>
						국적 : <select name="repNationCd">
								<option value="">전체</option>
								<option value="K"<c:if test="${param.repNationCd eq 'K'}"> selected="seleted"</c:if>>한국</option>
								<option value="F"<c:if test="${param.repNationCd eq 'F'}"> selected="seleted"</c:if>>외국</option>
							</select>
						<input type="submit" name="" value="조회">
					</form>
	</div>
	
	<%@ include file="../foot.jsp" %>

</body>
</html>
