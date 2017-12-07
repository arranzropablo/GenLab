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
	
	@Override
	public List<Problem> getAllProblems() {
		return (List<Problem>) this.problemsRepository.findAll();
	}

	@Override
	public Problem getProblem(int id) {
		return this.problemsRepository.findOne(id);
	}

	@Override
	public int saveProblem(Problem p) {
		return this.problemsRepository.save(p).getId();
	}

	@Override
	public void deleteProblem(int id) {
		this.problemsRepository.delete(id);
		
	}

	@Override
	public List<Problem> getProblemsBySection(int sectionid) {
		return problemsRepository.findBySectionid(sectionid);
	}

}
