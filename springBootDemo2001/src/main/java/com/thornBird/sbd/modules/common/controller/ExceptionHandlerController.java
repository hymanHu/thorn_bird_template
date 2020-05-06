package com.thornBird.sbd.modules.common.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thornBird.sbd.modules.common.vo.Result;
import com.thornBird.sbd.modules.common.vo.Result.ResultStatus;

/**
 * @Description: Exception Handler Controller
 * @author: HymanHu
 * @date: 2020年5月6日
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerController {
	
	@ExceptionHandler(value=AuthorizationException.class)
	@ResponseBody
	public Result<String> errorPage403() {
		return new Result<String>(ResultStatus.FAILED.status, "", "/common/403");
	}
}
