package com.crezam.endpoints.service;

import java.util.List;

import com.crezam.endpoints.entity.JobPosting;

public interface CrezamService {

	public List<JobPosting> getAllJobPostings();

	public JobPosting getJobPostingById(Long id) throws Exception;

	public JobPosting createNewJobPosting(JobPosting book) throws Exception;

	public JobPosting updateJobPostingById(Long id, JobPosting book) throws Exception;

	public boolean deleteJobPostingById(Long id) throws Exception;
}
