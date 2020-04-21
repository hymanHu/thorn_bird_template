package com.thornBird.springBootDemo.modules.account.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thornBird.springBootDemo.modules.account.dao.UserDao;
import com.thornBird.springBootDemo.modules.account.dao.UserRoleDao;
import com.thornBird.springBootDemo.modules.account.entity.Role;
import com.thornBird.springBootDemo.modules.account.entity.User;
import com.thornBird.springBootDemo.modules.account.service.UserService;
import com.thornBird.springBootDemo.modules.common.vo.Result;
import com.thornBird.springBootDemo.modules.common.vo.Result.ResultStatus;
import com.thornBird.springBootDemo.modules.common.vo.SearchVo;
import com.thornBird.springBootDemo.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public Result insertUser(User user) {
		Result result = new Result(ResultStatus.SUCCESS.status, "");
		
		User userTemp = userDao.getUserByUserName(user.getUserName());
		if (userTemp != null) {
			result.setStatus(ResultStatus.FAILED.status);
			result.setMessage("User name is repeat.");
			return result;
		}
		
		user.setCreateDate(new Date());
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		userDao.insertUser(user);
		
		List<Role> roles = user.getRoles();
		if (roles != null) {
			userRoleDao.deletUserRoleByUserId(user.getUserId());
			for (Role role : roles) {
				userRoleDao.addUserRole(role.getRoleId(), user.getUserId());
			}
		}
		
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken usernamePasswordToken = 
					new UsernamePasswordToken(user.getUserName(), user.getPassword());
			
			subject.login(usernamePasswordToken);
			subject.checkRoles();
		} catch (Exception e) {
			result.setStatus(ResultStatus.FAILED.status);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Override
	public Result login(User user) {
		Result result = new Result(ResultStatus.SUCCESS.status, "");
		
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken usernamePasswordToken = 
					new UsernamePasswordToken(user.getUserName(), MD5Util.getMD5(user.getPassword()));
			usernamePasswordToken.setRememberMe(user.getRememberMe());
			
			subject.login(usernamePasswordToken);
			subject.checkRoles();
		} catch (Exception e) {
			result.setStatus(ResultStatus.FAILED.status);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

	@Override
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
		searchVo.initSearchVo(searchVo);
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		List<User> users = Optional.ofNullable(userDao.getUsersBySearchVo(searchVo))
				.orElse(Collections.emptyList());
		return new PageInfo(users);
	}

	@Override
	public User getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public Result updateUser(User user) {
		
		Result result = new Result(ResultStatus.SUCCESS.status, "");
		
		User userTemp = userDao.getUserByUserName(user.getUserName());
		if (userTemp != null && user.getUserId() != userTemp.getUserId()) {
			result.setStatus(ResultStatus.FAILED.status);
			result.setMessage("User name is repeat.");
			return result;
		}
		
		userDao.updateUser(user);
		List<Role> roles = user.getRoles();
		if (!roles.isEmpty()) {
			userRoleDao.deletUserRoleByUserId(user.getUserId());
			for (Role role : roles) {
				userRoleDao.addUserRole(role.getRoleId(), user.getUserId());
			}
		}
		
		return result;
	}

	@Override
	public Result deleteUser(int userId) {
		Result result = new Result(ResultStatus.SUCCESS.status, "");
		try {
			userDao.deleteUser(userId);
		} catch (Exception e) {
			result.setStatus(ResultStatus.FAILED.status);
			result.setMessage(e.getMessage());
			return result;
		}
		return result;
	}
}
