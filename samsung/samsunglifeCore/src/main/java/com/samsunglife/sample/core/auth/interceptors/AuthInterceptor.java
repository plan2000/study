package com.samsunglife.sample.core.auth.interceptors;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.samsunglife.sample.core.SamsungConst;
import com.samsunglife.sample.core.SessionObject;
import com.samsunglife.sample.core.auth.CheckAuth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor{

	
	Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		
		try {
			HandlerMethod method2 = (HandlerMethod)handler;
		} catch (Exception e) {
			return true;
		}
		HandlerMethod method = (HandlerMethod)handler;
		
		CheckAuth auth = method.getMethodAnnotation(CheckAuth.class);
		try {

			logger.info("check auth {}",auth.level());	
		} catch (Exception e) {
			// TODO: handle exception
		}
		//어노테이션이 없거나 있는데 value를 false로 준경우.
		if(auth == null ){
			return true;
		}else {

			HttpSession session = request.getSession();
			Object ht = session.getAttribute(SamsungConst.sessionKeyName);
			if(ht!=null) {
				logger.info("tag Name {}",(String)session.getAttribute("tag"));
				
				HashMap<String, String> hm =(HashMap)ht;
				logger.info("hm hm {}",hm);
				if(hm.get(SamsungConst.enabler).equals("true")) {
					return true;
				}else {
					throw new Exception();
				}
			}else {
				throw new Exception();
			}
		}
		
		
		
		//Account account = (Account)session.getAttribute("account");
		//if(account == null || auth.level() > account.getLevel()){
			
		//}
		// 세션체크
		//HttpSession session = request.getSession();
		//로그인시 Account 객체가 세션에 담겨있습니다.
		//Account account = (Account)session.getAttribute("account");
		// 로그인 체크 & 레벨 체크
		//if(account == null || auth.level() > account.getLevel()){
		//	return false;
		//}
		
		
		//return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}