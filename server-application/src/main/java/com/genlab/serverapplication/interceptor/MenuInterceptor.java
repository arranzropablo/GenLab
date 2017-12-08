package com.genlab.serverapplication.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.genlab.serverapplication.utils.SectionProperties;

public class MenuInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	SectionProperties section;
	
	@Override
	public void postHandle(	HttpServletRequest request,
							HttpServletResponse response, 
							Object handler,
							ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
			if (modelAndView!=null) {
				SectionProperties menuModel = (SectionProperties) modelAndView.getModel().get("linkSectionProperties");
				
				if(menuModel==null){
					modelAndView.addObject("linkSectionProperties", section);
				}
			}
		}
}
