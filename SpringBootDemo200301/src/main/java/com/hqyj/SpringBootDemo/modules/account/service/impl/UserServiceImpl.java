package com.hqyj.SpringBootDemo.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.config.ResourceConfigBean;
import com.hqyj.SpringBootDemo.modules.account.dao.UserDao;
import com.hqyj.SpringBootDemo.modules.account.dao.UserRoleDao;
import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.utils.FileUtil;
import com.hqyj.SpringBootDemo.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private ResourceConfigBean resourceConfigBean;

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Override
	public Result<User> login(User user) {
		try {
			Subject subject = SecurityUtils.getSubject();
			
			UsernamePasswordToken usernamePasswordToken = 
					new UsernamePasswordToken(user.getUserName(), MD5Util.getMD5(user.getPassword()));
//			UsernamePasswordToken usernamePasswordToken = 
//					new UsernamePasswordToken(user.getAccountName(), MD5Util.getMD5(user.getPassword()));
			usernamePasswordToken.setRememberMe(user.getRememberMe());
			
			subject.login(usernamePasswordToken);
			subject.checkRoles();
			
			Session session = subject.getSession();
			User userTemp = (User) subject.getPrincipal();
			session.setAttribute("userId", userTemp.getUserId());
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<User>(ResultStatus.FAILED.status, "User name or password error.");
		}
		
		return new Result<User>(ResultStatus.SUCCESS.status, "Login success.", user);
	}

	@Override
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}

	@Override
	public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
		searchVo.initSearchVo();
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo<User>(
				Optional.ofNullable(userDao.getUsersBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
	}

	@Override
	public User getUserByUserId(int userId) {
		return userDao.getUserByUserId(userId);
	}

	@Override
	public Result<Object> deleteUser(int userId) {
		userDao.deleteUser(userId);
		userRoleDao.deletUserRoleByUserId(userId);
		return new Result<Object>(ResultStatus.SUCCESS.status, "Delete success.");
	}

	@Override
	@Transactional
	public Result<User> editUser(User user) {
		User userTemp = getUserByUserName(user.getUserName());
		if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
			return new Result<User>(ResultStatus.FAILED.status, "User name is repeat.");
		}
		
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		user.setCreateDate(LocalDateTime.now());

		// 管理员编辑用户信息时，只修改用户角色
		if (user.getUserId() > 0) {
//			userDao.updateUser(user);
			userRoleDao.deletUserRoleByUserId(user.getUserId());
		} else {
			userDao.insertUser(user);
		}

		List<Role> roles = user.getRoles();
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				userRoleDao.addUserRole(user.getUserId(), role.getRoleId());
			}
		}

		return new Result<User>(ResultStatus.SUCCESS.status, "Edit success.", user);
	}

	@Override
	public Result<String> uploadUserImage(MultipartFile userImage) {
		
		if (userImage.isEmpty()) {
			return new Result<>(ResultStatus.FAILED.status, "User image is empty.");
		}
		if (!FileUtil.isImage(userImage)) {
			return new Result<>(ResultStatus.FAILED.status, "File is not a image.");
		}
		
		String originalFilename = userImage.getOriginalFilename();
		String relatedPath = resourceConfigBean.getResourcePath() + originalFilename;
		String destPath = String.format("%s%s", resourceConfigBean.getLocalPathForWindow(), originalFilename);
		try {
			File destFile = new File(destPath);
			userImage.transferTo(destFile);
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
			return new Result<>(ResultStatus.FAILED.status, "File upload error.");
		}
		
		return new Result<>(ResultStatus.SUCCESS.status, "File upload success.", relatedPath);
	}

	@Override
	@Transactional
	public Result<User> updateUserProfile(User user) {
		User userTemp = getUserByUserName(user.getUserName());
		if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
			return new Result<User>(ResultStatus.FAILED.status, "User name is repeat.");
		}

		userDao.updateUser(user);

		return new Result<User>(ResultStatus.SUCCESS.status, "Edit success.", user);
	}

}
