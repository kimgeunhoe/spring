package com.myweb.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.repository.CommentDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.myweb.www.config.RootConfig.class})
public class CommentReposTest {
	private static Logger log = LoggerFactory.getLogger(CommentReposTest.class);
	
	@Inject
	private CommentDAO cdao;
	
	@Test
	public void insertCommentTest() throws Exception {
		int isUp = cdao.insert(new CommentVO(2, "tester2@tester.com", "test"));
		log.info(">>> insertCommentTest : {}", isUp > 0 ? "ok" : "fail"); 
	}
	
	@Test
	public void selectCommentListTest() throws Exception {
		for(CommentVO cvo : cdao.selectList(2)) {
			log.info("cvo : {}", cvo);
		}
	}
	
	@Test
	public void updateCommentTest() throws Exception {
		int isUp = cdao.update(new CommentVO(2, "tester update"));
		log.info(">>> updateCommemtTest : {}", isUp > 0 ? "ok" : "fail");
	}
	
	@Test
	public void deleteCommentTest() throws Exception {
		int isUp = cdao.delete(2);
		log.info(">>> deleteCommemtTest : {}", isUp > 0 ? "ok" : "fail");
	}
}
