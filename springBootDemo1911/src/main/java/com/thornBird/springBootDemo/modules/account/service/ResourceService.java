package com.thornBird.springBootDemo.modules.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.thornBird.springBootDemo.modules.account.entity.Resource;
import com.thornBird.springBootDemo.modules.common.vo.Result;
import com.thornBird.springBootDemo.modules.common.vo.SearchVo;


public interface ResourceService {

	Result editResource(Resource resource);
	
	Result deleteResource(int resourceId);
	
	PageInfo<Resource> getResources(SearchVo searchVo);
	
	List<Resource> getResourcesByRoleId(int roleId);
	
	Resource getResourceById(int resourceId);
}
