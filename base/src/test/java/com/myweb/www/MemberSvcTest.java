package com.myweb.www;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.MemberVO;
import com.myweb.www.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.myweb.www.config.RootConfig.class})
public class MemberSvcTest {
	private static Logger log = LoggerFactory.getLogger(MemberSvcTest.class);
	
	@Inject
	private MemberService msv;
	
	@Test
	public void removeMemberTest() throws Exception {
		int isUp = msv.remove("tester3@tester.com");
		log.info(">>> Test of remove  Member : {} ", isUp > 0 ? "OK" : "FAIL");
	}
	
	@Test
	public void modifyMember() throws Exception {
		int isUp = msv.modify(new MemberVO("tester@tester.com", "1111", "tester", 50));
		log.info(">>> Test of modify Member : {} ", isUp > 0 ? "OK" : "FAIL");
	}
	
	@Test
	public void getDetailMemberTest() throws Exception {
		MemberVO mvo = msv.getDetail("guest1@guest.com");
		log.info(">>> {}, {}, {}, {}, {}", mvo.getEmail(), mvo.getNickName(), mvo.getRegAt(), mvo.getLastLogin(), mvo.getGrade());
	}
	
	@Test
	public void getListMemberTest() throws Exception {
		List<MemberVO> list = msv.getList();
		for(MemberVO mvo : list) {
			log.info(">>> {}, {}, {}, {}, {}", mvo.getEmail(), mvo.getNickName(), mvo.getRegAt(), mvo.getLastLogin(), mvo.getGrade());
		}
	}
	
	@Test
	public void loginMemberSvcTest() throws Exception {
		MemberVO mvo = msv.login(new MemberVO("guest1@guest.com", "1111"));
		log.info(">>> {}, {}, {}, {}", mvo.getEmail(), mvo.getNickName(), mvo.getLastLogin(), mvo.getGrade());
	}
	
	@Test
	public void insertMemberSvcTest() throws Exception {
		int isUp = msv.register(new MemberVO("tester3@tester.com", "1111", "TESTER3"));
		log.info(">>> Test of register Member : {} ", isUp > 0 ? "OK" : "FAIL");
	}
}
