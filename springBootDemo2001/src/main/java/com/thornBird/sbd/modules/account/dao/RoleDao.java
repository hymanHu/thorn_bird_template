package com.thornBird.sbd.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.thornBird.sbd.modules.account.entity.Role;

@Repository
@Mapper
public interface RoleDao {

	@Insert("insert role(role_name) value(#{roleName})")
	@Options(useGeneratedKeys=true, keyProperty="roleId", keyColumn="role_id")
	void addRole(Role role);
	
	@Update("update role set role_name = #{roleName} where role_id = #{roleId}")
	void updateRole(Role role);
	
	@Delete("delete from role where role_id = #{roleId}")
	void deleteRole(int roleId);
	
	@Select("select * from role")
	List<Role> getRoles();
	
	@Select("select * from role role left join user_role userRole "
			+ "on role.role_id = userRole.role_id where userRole.user_id = #{userId}")
	List<Role> getRolesByUserId(int userId);
	
	@Select("select * from role role left join role_resource roleResource "
			+ "on role.role_id = roleResource.role_id where roleResource.resource_id = #{resourceId}")
	List<Role> getRolesByResourceId(int resourceId);
	
	@Select("select * from role where role_id=#{roleId}")
	Role getRoleById(int roleId);
	
}
