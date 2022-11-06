package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.BoardVO;

public interface BoardDAO {
	int insert(BoardVO bvo);
	List<BoardVO> selectList(PagingVO pgvo);
	BoardVO selectOne(long bno);
	int update(BoardVO bvo);
	int delete(long bno);
	int selectTotalCount(PagingVO pgvo);
	long selectLastBno();
	int updateReadCount(@Param("bno") long bno, @Param("i") int i);
	int updateCmtQty(@Param("bno") long bno, @Param("i") int i);
}
