package com.myweb.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductVO;
import com.myweb.www.repository.ProductDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.myweb.www.config.RootConfig.class})
public class ProductReposTest {
	
	@Inject
	private ProductDAO pdao;
	
	@Test
	public void insertProductDummyTest() throws Exception {
		int isUp = pdao.insert(new ProductVO("cate1", "P-A", 100, "tester2@tester.com", "DESC-A", "TESTERS"));
		log.info(">>> insertProductDummyTest > {}", isUp > 0 ? "ok" : "fail");
	}
	
	@Test
	public void insertProductDummiesTest() throws Exception {
		int isUp = 1;
		for(int i=100; i<200; i++) {
			isUp = pdao.insert(new ProductVO("cate"+((int)i/10), "P-A"+i, 100+i, "tester"+i+"@tester.com", "DESC-A"+i, "TESTERS"));
		}
		log.info(">>> insertProductDummiesTest > {}", isUp > 0 ? "ok" : "fail");
	}
	
	@Test
	public void selectProductListTest() throws Exception {
		for(ProductVO pvo : pdao.selectList(new PagingVO())) {
			log.info("{}", pvo);
		}
	}
	
	@Test
	public void selectProductTest() throws Exception {
		ProductVO pvo = pdao.selectOne(99L);
		log.info(">>> selectProductTest > {}", pvo);
	}
	
	@Test
	public void updateProductTest() throws Exception {
		int isUp = pdao.update(new ProductVO(88L, "cate11", "P-A", 100, "DESC-ABA", "TESTERS_UP"));
		log.info(">>> updateProductTest > {}", isUp > 0 ? "ok" : "fail");
	}
	
	@Test
	public void deleteProductTest() throws Exception {
		int isUp = pdao.delete(88L);
		log.info(">>> deleteProductTest > {}", isUp > 0 ? "ok" : "fail");
	}
}
