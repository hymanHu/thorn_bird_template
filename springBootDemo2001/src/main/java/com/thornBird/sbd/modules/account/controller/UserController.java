package com.thornBird.sbd.modules.account.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.thornBird.sbd.modules.account.entity.User;
import com.thornBird.sbd.modules.account.serrvice.UserService;
import com.thornBird.sbd.modules.common.vo.Result;
import com.thornBird.sbd.modules.common.vo.SearchVo;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/register", consumes = "application/json")
	public Result<User> register(@RequestBody User user) {
		return userService.insertUser(user);
	}
	
	@PostMapping(value="/login", consumes="application/json")
	public Result<User> getUser(@RequestBody User user) {
		return userService.login(user);
	}
	
	@PostMapping(value="/users", consumes="application/json")
	public PageInfo<User> getUsersBySearchVo(@RequestBody SearchVo searchVo) {
		return userService.getUsersBySearchVo(searchVo);
	}
	
	@RequestMapping("/user/{userId}")
	public User getUserById(@PathVariable int userId) {
		return userService.getUserById(userId);
	}
	
	@PutMapping(value="/user", consumes="application/json")
	public Result<User> updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/user/{userId}")
	@RequiresPermissions(value={"deleteUser"}, logical= Logical.OR) 
	public Result<User> deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}
	
}
