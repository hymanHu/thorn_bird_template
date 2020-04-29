package com.thornBird.sbd.modules.account.serrvice;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.thornBird.sbd.modules.account.entity.Role;
import com.thornBird.sbd.modules.common.vo.Result;
import com.thornBird.sbd.modules.common.vo.SearchVo;

public interface RoleService {

	Result<Role> editRole(Role role);
	
	Result<Role> deleteRole(int roleId);
	
	PageInfo<Role> getRoles(SearchVo searchVo);
	
	List<Role> getRolesByUserId(int userId);
	
	List<Role> getRolesByResourceId(int resourceId);
	
	Role getRoleById(int roleId);
	
	List<Role> getRoles();
}
