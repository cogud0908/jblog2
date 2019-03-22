package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserDao;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void join(UserVo userVo) {
		userDao.join(userVo);
	}

	public UserVo login(String id, String password) {
		UserVo userVo = userDao.login(id,password);
		return userVo;
	}
	
	
}
