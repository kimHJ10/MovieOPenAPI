<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	
	$(document).ready(function() {
		
		// ListPage
		var moveForm = $("#moveForm");
		$(".page-item a").on("click", function(e) {
			e.preventDefault();
			
			moveForm.find("input[name='curPage']").val($(this).attr("href"));
			moveForm.submit();
		});
		
		
		// MovieInfo
		$(".movieView").on("click", function(e) {
			e.preventDefault();
			
			moveForm.append("<input type='hidden' name='movieCd' value='"+$(this).attr("href")+"'>");
			moveForm.attr("action", "movieInfo");
			moveForm.submit();
		});
		
	});
	
	
</script>