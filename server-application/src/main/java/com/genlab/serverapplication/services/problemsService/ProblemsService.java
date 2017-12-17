package com.genlab.serverapplication.services.problemsService;

import java.util.List;

import com.genlab.serverapplication.models.Problem;

public interface ProblemsService {
	public List<Problem> getAllProblems();
	
	public Problem getProblem(int id);
	
	public int saveProblem(Problem p);
	
	public void deleteProblem(int id);
	
	public List<Problem> getProblemsBySection(int sectionid);
	
	public boolean existsProblem(int id);
	
}
