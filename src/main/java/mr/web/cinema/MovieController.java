package mr.web.cinema;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

@Controller
public class MovieController {
	
	
	@RequestMapping("/movieList")
	public String movieList(Model model, HttpServletRequest request, HttpServletResponse response) 
			throws OpenAPIFault, Exception {
		
		// KOBIS KEY
		String key = "30fe9b86c431db7f6bda9e920d923b8d";
		
		// KOBIS OPEN REST API 호출 
		KobisOpenAPIRestService kobis = new KobisOpenAPIRestService(key);

		// 현재페이지
		String curPage = request.getParameter("curPage")==null?"1":request.getParameter("curPage");
		
		// 결과 ROW수
		String itemPerPage = request.getParameter("itemPerPage")==null?"17":request.getParameter("itemPerPage");
		
		// 영화명
		String movieNm = request.getParameter("movieNm")==null?"":request.getParameter("movieNm");
		
		// 감독명
		String directorNm = request.getParameter("directorNm")==null?"":request.getParameter("directorNm");
		
		// 개봉연도 시작조건 (YYYY)
		String openStartDt = request.getParameter("openStartDt")==null?"":request.getParameter("openStartDt");
		
		// 개봉연도 끝조건 (YYYY)
		String openEndDt = request.getParameter("openEndDt")==null?"":request.getParameter("openEndDt");
		
		// 제작연도 시작조건 (YYYY)
		String prdtStartYear = request.getParameter("prdtStartYear")==null?"":request.getParameter("prdtStartYear");
		
		// 제작연도 끝조건 (YYYY)
		String prdtEndYear = request.getParameter("prdtEndYear")==null?"":request.getParameter("prdtEndYear");
		
		// 대표 국적코드 (공통코드서비스에서 '2204'로 조회된 국가코드)
		String repNationCd = request.getParameter("repNationCd")==null?"":request.getParameter("repNationCd");
		
		// 영화형태  코드 배열 (공통코드 서비스에서 '2201'로 조회된 영화코드)
		String[] movieTypeCdArr = request.getParameterValues("movieTypeCdArr")==null? null:request.getParameterValues("movieTypeCdArr");
		
		// 영화 코드 조회 서비스 호출
		String movieCdResponse = kobis.getMovieList(true, curPage, itemPerPage, movieNm, directorNm, openStartDt, openEndDt, prdtStartYear, prdtEndYear, repNationCd, movieTypeCdArr);
		
		// JSON 라이브러리를 통해 Handling
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String,Object> result = mapper.readValue(movieCdResponse, HashMap.class);
		request.setAttribute("result",result);
		
		// KOBIS OPEN API 코드 서비스 호출
		String nationCdResponse = kobis.getComCodeList(true,"2204");
		HashMap<String,Object> nationCd = mapper.readValue(nationCdResponse, HashMap.class);
		request.setAttribute("nationCd",nationCd);
		
		String movieTypeCdResponse = kobis.getComCodeList(true,"2201");
		HashMap<String,Object> movieTypeCd = mapper.readValue(movieTypeCdResponse, HashMap.class);
		request.setAttribute("movieTypeCd",movieTypeCd);
		
		
		
		// PageNavigation
		// 한 블럭에 보여줄 페이지 버튼 수
		int blockSize = 10;

		// 현재 페이지 블럭 위치
		int blockNum = (Integer.parseInt(curPage)-1)/blockSize;
		
		// 최대 출력 페이지
		int totalPage = 100;
		
		// 블럭의 시작값
		int blockStart = (blockSize * blockNum)+1;
		
		// 블럭 마지막 값
		int blockEnd = blockStart + (blockSize -1);
		if (blockEnd > totalPage) {
			blockEnd = totalPage;
		}
		
		// 이전페이지
		int prevPage = blockStart - 1;
		
		// 다음  페이지
		int nextPage = blockEnd + 1;
		
		
		// MODEL
		model.addAttribute("curPage",curPage);
		model.addAttribute("itemPerPage",itemPerPage);
		model.addAttribute("directorNm",directorNm);		
		model.addAttribute("movieNm",movieNm);		
		model.addAttribute("openStartDt",openStartDt);		
		model.addAttribute("movieTypeCdArr",movieTypeCdArr);		
		
		model.addAttribute("blockStart", blockStart);
		model.addAttribute("blockEnd", blockEnd);
		model.addAttribute("prevPage", prevPage);
		model.addAttribute("nextPage", nextPage);
		model.addAttribute("totalPage", totalPage);

		
		return "/page/movieList";
		
	}
	
	
	@RequestMapping ("/movieInfo")
	public String MovieInfo(Model model, HttpServletRequest request, HttpServletResponse response) 
			throws OpenAPIFault, Exception {
		// KOBIS KEY
		String key = "30fe9b86c431db7f6bda9e920d923b8d";
				
		// KOBIS OPEN REST API 호출 
		KobisOpenAPIRestService kobis = new KobisOpenAPIRestService(key);
		
		// 영화 코드
		String movieCd = request.getParameter("movieCd")==null?"":request.getParameter("movieCd");
		
		// 영화 코드 조회 서비스 호출
		String movieCdResponse = kobis.getMovieInfo(true, movieCd);
		
		// JSON 라이브러리를 통해 Handling
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String,Object> result = mapper.readValue(movieCdResponse, HashMap.class);
		request.setAttribute("result",result);
		
		model.addAttribute("movieCd", movieCd);
		
		
		return "/page/movieInfo";
	}
	
	
}
