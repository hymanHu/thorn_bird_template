package com.thornBird.sbd.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.thornBird.sbd.modules.account.entity.Role;
import com.thornBird.sbd.modules.common.vo.SearchVo;

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
	
	@Select("<script>" + 
			"select * from role "
			+ "<where> "
			+ "<if test='keyWord != \"\" and keyWord != null'>"
			+ "and role_name like '%${keyWord}%' "
			+ "</if>"
			+ "</where>"
			+ "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>"
			+ "order by ${orderBy} ${sort}"
			+ "</when>"
			+ "<otherwise>"
			+ "order by role_id desc"
			+ "</otherwise>"
			+ "</choose>"
			+ "</script>")
	List<Role> getRolesBySearchVo(SearchVo searchVo);
	
	@Select("select * from role role left join user_role userRole "
			+ "on role.role_id = userRole.role_id where userRole.user_id = #{userId}")
	List<Role> getRolesByUserId(int userId);
	
	@Select("select * from role role left join role_resource roleResource "
			+ "on role.role_id = roleResource.role_id where roleResource.resource_id = #{resourceId}")
	List<Role> getRolesByResourceId(int resourceId);
	
	@Select("select * from role where role_id=#{roleId}")
	Role getRoleById(int roleId);
	
}
