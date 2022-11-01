package com.myweb.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.service.CommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.myweb.www.config.RootConfig.class})
public class CommentSvcTest {
	private static Logger log = LoggerFactory.getLogger(CommentSvcTest.class);
			
	@Inject
	private CommentService csv;
	
	@Test
	public void registerCommentSvcTest() throws Exception {
		int isUp = csv.post(new CommentVO(2, "tester2@tester.com", "test"));
		log.info(">>> insertCommentSvcTest : {}", isUp > 0 ? "ok" : "fail");
	}
	
	@Test
	public void insertCommentDummiesTest() throws Exception {
		for (int j = 1; j <= 256; j++) {
			int x = (int) (Math.random() * 256);
			for (int i = 0; i < x; i++) {
				csv.post(new CommentVO(j,
						"tester" + ((int) (Math.random() * 256)) + "@tester.com",
						"Comment Dummy Content for" + j));
			}
		}
	}
	
	@Test
	public void getListCommentListSvcTest() throws Exception {
		for(CommentVO cvo : csv.getList(2)) {
			log.info("{}", cvo);
		}
	}
	
	@Test
	public void modifyCommentSvcTest() throws Exception {
		int isUp = csv.modify(new CommentVO(3, "tester update"));
		log.info(">>> updateCommentSvcTest : {}", isUp > 0 ? "ok" : "fail");
	}

	@Test
	public void removeCommentSvcTest() throws Exception {
		int isUp = csv.remove(3);
		log.info(">>> removeCommentSvcTest : {}", isUp > 0 ? "ok" : "fail");
	}
}
