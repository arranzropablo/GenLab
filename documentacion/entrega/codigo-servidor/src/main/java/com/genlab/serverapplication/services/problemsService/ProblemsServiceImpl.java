package com.genlab.serverapplication.services.problemsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.Problem;
import com.genlab.serverapplication.repositories.ProblemRepository;

@Service
public class ProblemsServiceImpl implements ProblemsService{

	@Autowired
	private ProblemRepository problemsRepository;
	
	public List<Problem> getAllProblems() {
		return (List<Problem>) this.problemsRepository.findAll();
	}

	public Problem getProblem(int id) {
		return this.problemsRepository.findOne(id);
	}

	public int saveProblem(Problem p) {
		return this.problemsRepository.save(p).getId();
	}

	public void deleteProblem(int id) {
		this.problemsRepository.delete(id);
	}

	public List<Problem> getProblemsBySection(int sectionid) {
		return problemsRepository.findBySectionid(sectionid);
	}
	
	public boolean existsProblem(int id) {
		return problemsRepository.exists(id);
	}
	
}
