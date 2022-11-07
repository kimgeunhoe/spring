package com.myweb.www.repository;

import com.myweb.www.domain.ProfileVO;

public interface ProfileDAO {
	int insertProfile(ProfileVO prvo);
	ProfileVO selectProfile(String email);
	int deleteProfile(String uuid);
}
