package mr.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mr.web.model.MemberDTO;

public interface MemberMapper {

	// 회원 리스트
	@Select("select * from memberDB")
	public List<MemberDTO> memberList();
	
	// 회원 정보 저장
	@Insert("insert into memberDB(id, pw, name, email) \r\n" + 
			"values(#{id}, #{pw}, #{name}, #{email})")
	public int memberInsert(MemberDTO dto);
	
	// 회원 정보 삭제
	@Delete("delete from memberDB where num=#{num}")
	public int memberDelete(int num);
	
	// 회원 정보 확인
	@Select("select * from memberDB where num=#{num}")
	public MemberDTO memberInfo(int num);

	// 회원 정보 변경
	@Update("update memberDB set name=#{name}, email=#{email} where num=#{num}")
	public int memberUpdate(MemberDTO dto);
	
	// 로그인
	@Select("select name from memberDB where id=#{id} AND pw=#{pw}")
	public String selectMemberName(MemberDTO dto);
	
}
