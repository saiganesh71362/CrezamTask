package com.crezam.endpoints.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crezam.endpoints.entity.JobPosting;
import com.crezam.endpoints.exceptionhandle.IdNotFoundException;
import com.crezam.endpoints.serviceimpl.CrezamServiceImpl;

@RestController
@RequestMapping("/jobpostng")
public class CrezamController {
	private static final Logger logger = LogManager.getLogger(CrezamController.class);

	CrezamServiceImpl crezamServiceImpl;

	// Constructor Injection
	public CrezamController(CrezamServiceImpl crezamServiceImpl) {
		this.crezamServiceImpl = crezamServiceImpl;
	}

	@GetMapping("/allJobs")
	public ResponseEntity<List<JobPosting>> getAllJobs() {
		logger.info("Received Request To Get All Jobs");
		List<JobPosting> allJobPostings = crezamServiceImpl.getAllJobPostings();
		logger.info("Successfully Retrieved {} Jobs Availabe ", allJobPostings.size());
		return new ResponseEntity<List<JobPosting>>(allJobPostings, HttpStatus.FOUND);
	}

	@GetMapping("getJob/{id}")
	public ResponseEntity<JobPosting> getJobById(@PathVariable Long id) throws IdNotFoundException {
		logger.info("Received Request To Get Job By ID: {}", id);
		JobPosting jobPostingById = crezamServiceImpl.getJobPostingById(id);
		logger.info("Successfully Retrieved Job With ID: {}", id);
		return new ResponseEntity<JobPosting>(jobPostingById, HttpStatus.FOUND);
	}

	@PostMapping("/createNewJob")
	public ResponseEntity<JobPosting> createNewJob(@RequestBody JobPosting jobPosting) throws IdNotFoundException {
		logger.info("Request Received To Create A New Job: {}");
		JobPosting newJobPosting = crezamServiceImpl.createNewJobPosting(jobPosting);
		logger.info("Job Created Successfully: {}");
		return new ResponseEntity<JobPosting>(newJobPosting, HttpStatus.CREATED);
	}

	@PutMapping("/updateJob/{id}")
	public ResponseEntity<JobPosting> updateJobById(@PathVariable Long id, @RequestBody JobPosting jobPosting)
			throws Exception {
		logger.info("Received Request To Update Job With ID: {}", id);
		logger.debug("Job Details: {}", jobPosting);
		JobPosting updateJobPostingById = crezamServiceImpl.updateJobPostingById(id, jobPosting);
		logger.info("Successfully Updated Job With ID: {}", id);
		return new ResponseEntity<JobPosting>(updateJobPostingById, HttpStatus.OK);

	}

	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<Boolean> deleteJobId(@PathVariable Long id) throws IdNotFoundException {
		logger.info("Received Request To Delete Job with ID: {}", id);
		boolean deleteJobyId = crezamServiceImpl.deleteJobPostingById(id);
		logger.info("Successfully Deleted Job With ID: {}", id);
		return new ResponseEntity<Boolean>(deleteJobyId, HttpStatus.OK);
	}

}
