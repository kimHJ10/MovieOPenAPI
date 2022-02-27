package mr.web.model;

public class MemberDTO {
	
	private int num;
	private String id;
	private String pw;
	private String name;
	private String email;
	
	
	public MemberDTO() {}
	
	
	// 인자 생성
	public MemberDTO(int num, String id, String pw, String name, String email) {
		this.num = num;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
	}
	
	
	public MemberDTO(String id, String pw, String name, String email) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
	}


	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "MemberDTO [num : " + num + "\n id : " + id + "\n pw : " + pw + "\n name : " + name + "\n email :" + email + "]";
	}
	
	
	
}
