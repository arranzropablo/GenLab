package com.genlab.serverapplication.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.genlab.serverapplication.models.SectionsMapping;

public class CurrentSectionInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	HttpSession session;
	
	@Override
	public boolean preHandle(	HttpServletRequest request,
							HttpServletResponse response, 
							Object handler) throws Exception {
		
		super.preHandle(request, response, handler);
			if (session!=null) {
				
				String [] path = request.getServletPath().split("/");

				if(path.length > 2 && 
				   "home".equalsIgnoreCase(path[1]) && 
				   (session.getAttribute("currentSection") == null ||
 				   !((SectionsMapping)session.getAttribute("currentSection")).toString().toLowerCase().equalsIgnoreCase(path[2]))) {
						session.setAttribute("currentSection", SectionsMapping.valueOf(path[2].toUpperCase()));
					}	
			}
		return true;
	}
	
}
