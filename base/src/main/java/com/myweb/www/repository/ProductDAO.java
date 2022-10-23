package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductVO;

public interface ProductDAO {
	int insert(ProductVO pvo);
	List<ProductVO> selectList(PagingVO pgvo);
	ProductVO selectOne(long pno);
	int update(ProductVO pvo);
	int delete(long pno);
	int selectTotalCount(PagingVO pgvo);
	long selectLastPno();
	int updateReadCount(@Param("pno") long pno, @Param("i") int i);
	int updateCmtQty(@Param("pno") long pno, @Param("i") int i);
}
