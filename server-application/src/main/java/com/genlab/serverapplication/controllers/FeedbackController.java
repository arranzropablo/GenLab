package com.genlab.serverapplication.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.genlab.serverapplication.models.FeedbackItem;
import com.genlab.serverapplication.models.FeedbackItemView;
import com.genlab.serverapplication.models.FeedbackObject;
import com.genlab.serverapplication.models.FeedbackObjectView;
import com.genlab.serverapplication.models.SectionsMapping;
import com.genlab.serverapplication.models.Test;
import com.genlab.serverapplication.models.User;
import com.genlab.serverapplication.services.testsService.TestService;
import com.genlab.serverapplication.services.userService.UserService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TestService testService;

	@GetMapping("")
	public String getUsers(HttpServletRequest request, Model model, HttpSession session) {
		List<User> user_list = userService.getAllUsers();
		Gson gson = new Gson();
		if(user_list == null) {
			user_list = new ArrayList<User>();
		}
		List<FeedbackObjectView> feedback_list = new ArrayList<FeedbackObjectView>();
		for(User user: user_list) {
			if(!user.getRoles().toUpperCase().contains("ADMIN")) {
				FeedbackObject fo = gson.fromJson(user.getFeedback(), FeedbackObject.class);
				List<Integer> tests = fo.getRight().stream().map(FeedbackItem::getIdTest).collect(Collectors.toList());
				tests.addAll(fo.getWrong().stream().map(FeedbackItem::getIdTest).collect(Collectors.toList()));
				List<Integer> uniquetests = tests.stream().distinct().collect(Collectors.toList());
				List<FeedbackItemView> itemList = new ArrayList<>();
				List<Integer> test_list = testService.getTestBySection(((SectionsMapping)session.getAttribute("currentSection")).getId()).stream().map(Test::getId).collect(Collectors.toList());
				uniquetests.forEach(test ->{
					if (test_list.contains(test)) {
						int numRight = fo.getRight().stream().filter(item -> item.getIdTest() == test).collect(Collectors.toList()).size();
						int numWrong = fo.getWrong().stream().filter(item -> item.getIdTest() == test).collect(Collectors.toList()).size();
						try {
							FeedbackItemView item = FeedbackItemView.builder().numRightAnswers(numRight).numWrongAnswers(numWrong).testName(testService.getTest(test).getTitulo()).build();
							itemList.add(item);
							itemList.size();
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
				feedback_list.add(FeedbackObjectView.builder().user(user.getEmail()).item(itemList).build());
			}
		}
		model.addAttribute("feedback_list", feedback_list);
		return "feedback";
	}
}
