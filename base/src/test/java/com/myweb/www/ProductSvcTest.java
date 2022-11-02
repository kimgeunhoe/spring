package com.myweb.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.ProductVO;
import com.myweb.www.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.myweb.www.config.RootConfig.class})
public class ProductSvcTest {
	private static Logger log = LoggerFactory.getLogger(ProductSvcTest.class);
	
	@Inject
	private ProductService psv;
	
	@Test
	public void removeProductTest() throws Exception {
		int isUp = psv.remove(88L);
		log.info(">>> Test of remove Product : {} ", isUp > 0 ? "OK" : "FAIL");
	}
	
	@Test
	public void modifyProductTest() throws Exception {
		int isUp = psv.modify(new ProductVO(88L, "cate11", "P-A", 1000, "DESC-ABA", "TESTERS_UP_up"));
		log.info(">>> Test of modify Product : {} ", isUp > 0 ? "OK" : "FAIL");
	}
	
	@Test
	public void getDetailProductTest() throws Exception {
		ProductVO pvo = psv.getDetail(99L);
		log.info(">>> selectProductTest > {}", pvo);
	}
	
	@Test
	public void getListProductTest() throws Exception {
		for(ProductVO pvo : psv.getList()) {
			log.info("{}", pvo);
		}
	}
	
	@Test
	public void insertProductDummyTest() throws Exception {
		for (int i = 1; i <= 256; i++) {
			psv.register(
					new ProductVO(
							"cate"+(i%10), "S-A", (int)(Math.random()*5001)+5000,
							"tester"+(int)(Math.random()*100)+"@tester.com",
							"DESC-SA", "STAKEHOLDERS"));
		}
	}
	
	@Test
	public void insertProductSvcTest() throws Exception {
		int isUp = psv.register(new ProductVO("cate21", "S-A", 1100, "tester200@tester.com", "DESC-SA", "STEAKHOLDERS"));
		log.info(">>> Test of register Product : {} ", isUp > 0 ? "OK" : "FAIL");
	}
}
