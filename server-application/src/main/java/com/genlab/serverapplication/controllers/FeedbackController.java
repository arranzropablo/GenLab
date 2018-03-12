package com.genlab.serverapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.genlab.serverapplication.models.FeedbackObject;
import com.genlab.serverapplication.models.User;
import com.genlab.serverapplication.services.userService.UserService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private UserService service;

	@GetMapping("")
	public String getUsers(HttpServletRequest request, Model model, HttpSession session) {
		List <User> user_list = service.getAllUsers();
		Gson gson = new Gson();
		if(user_list == null) {
			user_list = new ArrayList<User>();
		}
		List<FeedbackObject> feedback_list = new ArrayList<FeedbackObject>();
		for(User u: user_list) {
			if(!u.getRoles().toUpperCase().contains("TEACHER") && !u.getRoles().toUpperCase().contains("ADMIN") ) {
				FeedbackObject fo = gson.fromJson(u.getFeedback(), FeedbackObject.class);
				feedback_list.add(fo);
			}
		}
		model.addAttribute("feedback_list", feedback_list);
		return "feedback";
	}
}
