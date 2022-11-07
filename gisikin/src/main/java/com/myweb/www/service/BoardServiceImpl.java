package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.FileDAO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.CommentDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO bdao;
	
	@Inject
	private FileDAO fdao;
	
	@Inject
	private CommentDAO cdao;
	
	@Transactional
	@Override
	public int register(BoardDTO bdto) {
		int isUp = bdao.insert(bdto.getBvo());
		
		if(isUp>0 && bdto.getFileList().size()>0) {
			long pno = bdao.selectLastBno();
			for (FileVO fvo : bdto.getFileList()) {
				fvo.setBno(pno);
				isUp *= fdao.insertFile(fvo);
			}
		}
		return isUp;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		return bdao.selectList(pgvo);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardDTO getDetail(long bno) {
		bdao.updateReadCount(bno, 1);
		return new BoardDTO(bdao.selectOne(bno), fdao.selectListFile(bno));
	}

	@Transactional
	@Override
	public int modify(BoardDTO bdto) {
		int isUp = bdao.update(bdto.getBvo());
		isUp = bdao.updateReadCount(bdto.getBvo().getBno(), -2);
		if(bdto.getFileList()!=null) {
			if(isUp>0 && bdto.getFileList().size()>0) {
				for (FileVO fvo : bdto.getFileList()) {
					fvo.setBno(bdto.getBvo().getBno());
					isUp *= fdao.insertFile(fvo);
				}
			}
		}
		return isUp;
	}

	@Transactional
	@Override
	public int remove(long bno) {
		int isUp = bdao.delete(bno);
		if(isUp > 0) {
			isUp = fdao.deleteAllFile(bno);
			isUp = cdao.deleteAll(bno);
		}
		return isUp;
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.selectTotalCount(pgvo);
	}

	@Override
	public int removeFile(String uuid) {
		return fdao.deleteFile(uuid);
	}

}
