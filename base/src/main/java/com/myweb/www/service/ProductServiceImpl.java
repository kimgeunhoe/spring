package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductDTO;
import com.myweb.www.domain.ProductVO;
import com.myweb.www.repository.FileDAO;
import com.myweb.www.repository.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {
	@Inject
	private ProductDAO pdao;
	
	@Inject
	private FileDAO fdao;
	
	@Override
	public int register(ProductDTO pdto) {
		int isUp = pdao.insert(pdto.getPvo());
		
		if(isUp>0 && pdto.getFileList().size()>0) {
			long pno = pdao.selectLastPno();
			for (FileVO fvo : pdto.getFileList()) {
				fvo.setPno(pno);
				isUp *= fdao.insertFile(fvo);
			}
		}
		return isUp;
	}

	@Override
	public List<ProductVO> getList(PagingVO pgvo) {
		return pdao.selectList(pgvo);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public ProductDTO getDetail(long pno) {
		pdao.updateReadCount(pno, 1);
		return new ProductDTO(pdao.selectOne(pno), fdao.selectListFile(pno));
	}

	@Override
	public int modify(ProductDTO pdto) {
		int isUp = pdao.update(pdto.getPvo());
		isUp = pdao.updateReadCount(pdto.getPvo().getPno(), -2);
		if(pdto.getFileList()!=null) {
			if(isUp>0 && pdto.getFileList().size()>0) {
				for (FileVO fvo : pdto.getFileList()) {
					fvo.setPno(pdto.getPvo().getPno());
					isUp *= fdao.insertFile(fvo);
				}
			}
		}
		return isUp;
	}

	@Override
	public int remove(long pno) {
		int isUp = pdao.delete(pno);
		if(isUp > 0) {
			isUp = fdao.deleteAllFile(pno);
		}
		return isUp;
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return pdao.selectTotalCount(pgvo);
	}

	@Override
	public int removeFile(String uuid) {
		return fdao.deleteFile(uuid);
	}

}
