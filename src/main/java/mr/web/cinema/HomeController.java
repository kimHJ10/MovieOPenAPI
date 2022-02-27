package mr.web.cinema;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String boxoffice(Model model, String[] args, HttpServletRequest request, HttpServletResponse response) 
			throws OpenAPIFault, Exception, IOException {

		// KOBIS KEY
		String key = "30fe9b86c431db7f6bda9e920d923b8d";
		
		// KOBIS OPEN REST API 호출 
		KobisOpenAPIRestService kobis = new KobisOpenAPIRestService(key);
		
		// 날짜 설정
		LocalDateTime time = LocalDateTime.now().minusDays(1);
		
		String minusDay = time.format(DateTimeFormatter.ofPattern("yyyy"+"년"+"MM"+"월"+"dd"+"일"));
		
		// targetDt에 날짜 입력
		String targetDt = request.getParameter("targetDt")==null?time.format(DateTimeFormatter.ofPattern("yyyyMMdd")):request.getParameter("targetDt");
		
		// 출력할 데이터 ROW값 : 최대 10개
		String itemPerPage = request.getParameter("itemPerPage")==null?"10":request.getParameter("itemPerPage");
		
		// 상업 영화 : "N", 다양성 영화 : "Y", 전체 : "default"
		String multiMovieYn = request.getParameter("multiMovieYn")==null?"":request.getParameter("multiMovieYn");
		
		// 한국 영화 : "K", 외국 영화 : "F", 전체 : "default"
		String repNationCd = request.getParameter("repNationCd")==null?"":request.getParameter("repNationCd");
		
		// 상영 지역별 코드 (전체 : "default")
		String wideAreaCd = request.getParameter("wideAreaCd")==null?"":request.getParameter("wideAreaCd");
		
		
		// DailyBoxoffice 서비스 호출
		String dailyResponse = kobis.getDailyBoxOffice(true, targetDt, itemPerPage, multiMovieYn, repNationCd, wideAreaCd);
		
		String movieCd = request.getParameter("movieCd")==null?"":request.getParameter("movieCd");
		
		// JSON 라이브러리를 통한 Handling
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> dailyResult = mapper.readValue(dailyResponse, HashMap.class);
		request.setAttribute("dailyResult", dailyResult);
		
		// KOBIS OPEN API 코드 서비스 호출
		String codeResponse = kobis.getComCodeList(true, "0105000000");
		HashMap<String, Object> codeResult = mapper.readValue(codeResponse, HashMap.class);
		request.setAttribute("codeResult", codeResult);
		
		
		model.addAttribute("kobis", kobis);
	    model.addAttribute("minusDay", minusDay);
	    model.addAttribute("targetDt", targetDt);
	    model.addAttribute("movieCd", movieCd);
		
		return "/page/home";
	}
	

}
