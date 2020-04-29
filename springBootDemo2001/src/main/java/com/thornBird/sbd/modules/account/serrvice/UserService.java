package com.thornBird.sbd.modules.account.serrvice;

import com.github.pagehelper.PageInfo;
import com.thornBird.sbd.modules.account.entity.User;
import com.thornBird.sbd.modules.common.vo.Result;
import com.thornBird.sbd.modules.common.vo.SearchVo;

public interface UserService {
	
	Result<User> insertUser(User user);
	
	User getUserByUserName(String userName);
	
	Result<User> login(User user);
	
	void logout();
	
	PageInfo<User> getUsersBySearchVo(SearchVo searchVo);
	
	User getUserById(int userId);
	
	Result<User> updateUser(User user);
	
	Result<User> deleteUser(int userId);
}
