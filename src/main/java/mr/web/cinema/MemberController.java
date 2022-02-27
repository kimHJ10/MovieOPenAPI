package mr.web.cinema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import mr.web.mapper.MemberMapper;
import mr.web.model.MemberDTO;

@Controller
public class MemberController {

	@Autowired
	private MemberMapper memberMapper;
	
	
	// 회원가입
	@RequestMapping ("/join")
	public String memberJoin() {
		
		return "/page/join";
	}
	
	
	// 회원정보 저장
	@RequestMapping("/memberInsert")
	public String memberInsert(MemberDTO dto){
		int cnt = memberMapper.memberInsert(dto);
		return "redirect:/";
	}
	
	
	// 로그인
	@RequestMapping("/login")
	public String loginProcess(MemberDTO dto, HttpSession session) {
		String userName = memberMapper.selectMemberName(dto);
		
		if(userName !=null && !"".contentEquals(userName)) {
			session.setAttribute("sessionUserId", dto.getId());
			session.setAttribute("sessionUserName", userName);
		}else { //실패
			session.setAttribute("sessionUserId", "");
			session.setAttribute("sessionUserName", "");
		}
		
		return "redirect:/";
	}
	
	
	// 로그아웃
	@RequestMapping ("/logout")
	public String memberLogout(HttpSession session) {
		session.removeAttribute("sessionUserId");
		return "redirect:/";
	}
	
	
}
