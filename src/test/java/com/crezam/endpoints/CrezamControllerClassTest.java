package com.crezam.endpoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.crezam.endpoints.controller.CrezamController;
import com.crezam.endpoints.entity.JobPosting;
import com.crezam.endpoints.serviceimpl.CrezamServiceImpl;

@SpringBootTest
class CrezamControllerClassTest {
	@Mock
	CrezamServiceImpl crezamServiceImpl;

	@InjectMocks
	CrezamController crezamController;

	@Test
	@Order(1)
	void test_getAllJobs() {
		ArrayList<JobPosting> jobList = new ArrayList<JobPosting>();
		// Create some job postings
		JobPosting job1 = new JobPosting("Software Engineer", "Develop and maintain software applications.",
				"New York, NY", "TechCorp", "$80,000 - $120,000", Arrays.asList("Java", "Spring Boot", "SQL"),
				LocalDate.of(2024, 12, 31));

		JobPosting job2 = new JobPosting("Data Scientist", "Analyze data and build predictive models.",
				"San Francisco, CA", "DataAnalytics Inc.", "$100,000 - $150,000",
				Arrays.asList("Python", "Machine Learning", "Statistics"), LocalDate.of(2024, 11, 30));
		jobList.add(job1);
		jobList.add(job2);

		when(crezamServiceImpl.getAllJobPostings()).thenReturn(jobList);

		ResponseEntity<List<JobPosting>> allJobs = crezamController.getAllJobs();

		assertEquals(HttpStatus.FOUND, allJobs.getStatusCode());
		assertEquals(2, allJobs.getBody().size());
	}

	@Test
	@Order(2)
	void test_getJobById() {
		Long id = 1l;
		JobPosting job1 = new JobPosting("Software Engineer", "Develop and maintain software applications.",
				"New York, NY", "TechCorp", "$80,000 - $120,000", Arrays.asList("Java", "Spring Boot", "SQL"),
				LocalDate.of(2024, 12, 31));

		when(crezamServiceImpl.getJobPostingById(id)).thenReturn(job1);
		ResponseEntity<JobPosting> jobById = crezamController.getJobById(id);
		assertEquals(HttpStatus.FOUND, jobById.getStatusCode());
		assertEquals(job1, jobById.getBody());
	}

	@Test
	@Order(3)
	void test_createNewJob() {
		JobPosting job3 = new JobPosting("Software Engineer", "Develop and maintain software applications.",
				"New York, NY", "TechCorp", "$80,000 - $120,000", Arrays.asList("Java", "Spring Boot", "SQL"),
				LocalDate.of(2024, 12, 31));

		when(crezamServiceImpl.createNewJobPosting(job3)).thenReturn(job3);
		ResponseEntity<JobPosting> newJob = crezamController.createNewJob(job3);
		assertEquals(HttpStatus.CREATED, newJob.getStatusCode());
		assertEquals(job3, newJob.getBody());
	}

	@Test
	@Order(4)
	void test_updateJobId() throws Exception {
		Long eid = 4l;
		JobPosting existingJob = new JobPosting("Software Engineer", "Develop and maintain software applications.",
				"New York, NY", "TechCorp", "$80,000 - $120,000", Arrays.asList("Java", "Spring Boot", "SQL"),
				LocalDate.of(2024, 12, 31));
		Long uid = 4l;

		JobPosting updatingJob = new JobPosting("Software Engineer", "Develop and maintain software applications.",
				"New York, NY", "TechCorp", "$80,000 - $120,000", Arrays.asList("Java", "Spring Boot", "SQL"),
				LocalDate.of(2024, 12, 31));

		when(crezamServiceImpl.updateJobPostingById(eid, updatingJob)).thenReturn(existingJob);
		ResponseEntity<JobPosting> response = crezamController.updateJobById(uid, updatingJob);

		// Then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(existingJob, response.getBody());
		assertEquals("Software Engineer", response.getBody().getTitile());
		assertEquals("Develop and maintain software applications.", response.getBody().getDescription());
		assertEquals("New York, NY", response.getBody().getLocation());
		assertEquals("TechCorp", response.getBody().getCompany());
		assertEquals("$80,000 - $120,000", response.getBody().getSalaryRange());
		assertEquals(Arrays.asList("Java", "Spring Boot", "SQL"), response.getBody().getRequiredSkills());
		assertEquals(LocalDate.of(2024, 12, 31), response.getBody().getApplicationDeadline());

	}

	@Test
	@Order(5)
	void test_deleteJobById() {
		Long id = 4l;
		JobPosting job4 = new JobPosting("Software Engineer", "Develop and maintain software applications.",
				"New York, NY", "TechCorp", "$80,000 - $120,000", Arrays.asList("Java", "Spring Boot", "SQL"),
				LocalDate.of(2024, 12, 31));

		when(crezamServiceImpl.deleteJobPostingById(id)).thenReturn(true);
		ResponseEntity<Boolean> response = crezamController.deleteJobId(id);

		// Then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(true, response.getBody());
	}

}
