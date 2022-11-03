package com.myweb.www.ctrl;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.CommentService;

@RequestMapping("/comment/*")
@Controller
public class CommentController {
	@Inject
	private CommentService csv;
	
	//주소가 post이면서 get메소드로 보내진 것을 매핑한다
	@PostMapping(value="/post", consumes="application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@RequestBody CommentVO cvo) {
		return csv.post(cvo) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//get메소드로 보내면 주소에 있는 값을 pathvariable을 이용해 bno, pageNo로 받는다.
	@GetMapping(value="/{bno}/{pageNo}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PagingHandler> spread(@PathVariable("bno") long bno, @PathVariable("pageNo") int pageNo) {
		PagingVO pgvo = new PagingVO(pageNo, 10);
		return new ResponseEntity<PagingHandler>(csv.spread(bno, pgvo), HttpStatus.OK);
	}
	
	//put메소드로 보내면 주소에 있는 값을 pathvariable을 이용해 cno로 받는다.
	@PutMapping(value="/{cno}", consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> edit(@PathVariable("cno") long cno, @RequestBody CommentVO cvo) {
		return csv.modify(cvo) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//delete메소드로 보내면 주소에 있는 값을 pathvariable을 이용해 cno로 받는다.
	@DeleteMapping(value= "/{cno}", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> delete(@PathVariable("cno") long cno) {
		return csv.remove(cno) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
