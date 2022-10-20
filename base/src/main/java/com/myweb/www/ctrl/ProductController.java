package com.myweb.www.ctrl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductDTO;
import com.myweb.www.domain.ProductVO;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	@Inject
	private ProductService psv;
	
	@Inject
	private FileHandler fhd;
	
	@GetMapping("/register")
	public void register() {
		log.info(">>> ProductController > register > register - GET");
	}
	
	@PostMapping("/register")
	public String register(ProductVO pvo, RedirectAttributes rttr, @RequestParam(name = "fileAttached", required = false) MultipartFile[] files) {
		log.info(">>> register-pvo : {}", pvo);
		List<FileVO> fileList = null;
		if(files[0].getSize()>0) {
			fileList = fhd.getFileList(files);
			pvo.setFileCount(fileList.size());
		}
		int isUp = psv.register(new ProductDTO(pvo, fileList));
		log.info(">>> product register - post : {}", isUp > 0 ? "ok" : "fail");
//		rttr.addAttribute()
		return "redirect:/product/list";
	}
	
	@GetMapping("/list")
	public void list(Model model, PagingVO pgvo) {
		log.info(">>> product list - get");
		model.addAttribute("list", psv.getList(pgvo));
		int totalCount = psv.getTotalCount(pgvo);
		
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(Model model, @RequestParam("pno") long pno, @ModelAttribute("pgvo") PagingVO pgvo) {
		log.info(">>> product detail - get");
		model.addAttribute("pdto", psv.getDetail(pno));
	}
	
//	@GetMapping("/modify")
//	public void modify(Model model, @RequestParam("pno") long pno) {
//		log.info(">>> product modify - get");
//		model.addAttribute("pvo", psv.getDetail(pno));
//	}
	
	@PostMapping("/modify")
	public String modify(ProductVO pvo, RedirectAttributes rttr, PagingVO pgvo,  @RequestParam(name = "fileAttached", required = false) MultipartFile[] files) {
		log.info(">>> product modify - post");
		List<FileVO> fileList = null;
		if (files[0].getSize()>0) {
			fileList = fhd.getFileList(files);
			pvo.setFileCount(fileList.size());
		}
		int isUp = psv.modify(new ProductDTO(pvo, fileList));
		log.info(">>> product modify : {}", isUp > 0 ? "ok" : "fail");
		rttr.addAttribute("pageNo", pgvo.getPageNo());
		rttr.addAttribute("qty", pgvo.getQty());
		rttr.addAttribute("type", pgvo.getType());
		rttr.addAttribute("kw", pgvo.getKw());
		return "redirect:/product/detail?pno="+pvo.getPno();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("pno") long pno, RedirectAttributes rttr, PagingVO pgvo) {
		log.info(">>> product remove - post");
		int isUp = psv.remove(pno);
		log.info(">>> produc remove : {}", isUp > 0 ? "ok" : "fail");
		rttr.addAttribute("pageNo", pgvo.getPageNo());
		rttr.addAttribute("qty", pgvo.getQty());
		rttr.addAttribute("type", pgvo.getType());
		rttr.addAttribute("kw", pgvo.getKw());
		return "redirect:/product/list";
	}
	
	@DeleteMapping(value="/file/{uuid}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> removeFile(@PathVariable("uuid") String uuid) {
		return psv.removeFile(uuid) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
