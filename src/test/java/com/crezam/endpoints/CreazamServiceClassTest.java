package com.crezam.endpoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.crezam.endpoints.entity.JobPosting;
import com.crezam.endpoints.repositoy.CrezamRepository;
import com.crezam.endpoints.serviceimpl.CrezamServiceImpl;

@SpringBootTest
class CreazamServiceClassTest {
	@Mock
	CrezamRepository crezamRepository;
	@InjectMocks
	CrezamServiceImpl crezamServiceImpl;

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

		when(crezamRepository.findAll()).thenReturn(jobList); // Mocking
		assertEquals(2, crezamServiceImpl.getAllJobPostings().size());

	}

	@Test
	@Order(2)
	void test_getJobById() throws Exception {
		JobPosting job1 = new JobPosting("Software Engineer", "Develop and maintain software applications.",
				"New York, NY", "TechCorp", "$80,000 - $120,000", Arrays.asList("Java", "Spring Boot", "SQL"),
				LocalDate.of(2024, 12, 31));
		Long id = 1l;
		when(crezamRepository.findById(id)).thenReturn(Optional.of(job1));
		assertEquals(job1, crezamServiceImpl.getJobPostingById(id));
	}

	@Test
	@Order(3)
	void test_createNewJob() throws Exception {

		JobPosting job1 = new JobPosting("Software Engineer", "Develop and maintain software applications.",
				"New York, NY", "TechCorp", "$80,000 - $120,000", Arrays.asList("Java", "Spring Boot", "SQL"),
				LocalDate.of(2024, 12, 31));
		when(crezamRepository.save(job1)).thenReturn(job1);
		assertEquals(job1, crezamServiceImpl.createNewJobPosting(job1));
		verify(crezamRepository, times(1)).save(job1);

	}

	@Test
	@Order(5)
	void test_updateBookById() throws Exception {
		JobPosting existingJob = new JobPosting(1L, "Software Engineer", "Develop and maintain software applications.",
				"New York, NY", "TechCorp", "$80,000 - $120,000", Arrays.asList("Java", "Spring Boot", "SQL"),
				LocalDate.of(2024, 12, 31));

		JobPosting updatedJob = new JobPosting(1L, "Senior Software Engineer",
				"Develop, maintain, and lead software applications.", "San Francisco, CA", "TechCorp",
				"$120,000 - $150,000", Arrays.asList("Java", "Spring Boot", "Microservices"),
				LocalDate.of(2025, 1, 31));

		when(crezamRepository.existsById(1L)).thenReturn(true);
		when(crezamRepository.findById(1L)).thenReturn(Optional.of(existingJob));
		when(crezamRepository.save(any(JobPosting.class))).thenReturn(updatedJob);

		// Act
		JobPosting result = crezamServiceImpl.updateJobPostingById(1L, updatedJob);

		// Assert
		assertNotNull(result);
		assertEquals(updatedJob.getTitile(), result.getTitile());
		assertEquals(updatedJob.getDescription(), result.getDescription());
		assertEquals(updatedJob.getLocation(), result.getLocation());
		assertEquals(updatedJob.getCompany(), result.getCompany());
		assertEquals(updatedJob.getSalaryRange(), result.getSalaryRange());
		assertEquals(updatedJob.getRequiredSkills(), result.getRequiredSkills());
		assertEquals(updatedJob.getApplicationDeadline(), result.getApplicationDeadline());

		verify(crezamRepository).save(existingJob);
	}

	@Test
	@Order(5)
	void test_deleteJobById() throws Exception {
		Long id = 1L;
		JobPosting job1 = new JobPosting("Software Engineer", "Develop and maintain software applications.",
				"New York, NY", "TechCorp", "$80,000 - $120,000", Arrays.asList("Java", "Spring Boot", "SQL"),
				LocalDate.of(2024, 12, 31));
		when(crezamRepository.findById(id)).thenReturn(Optional.of(job1));

		assertEquals(true, crezamServiceImpl.deleteJobPostingById(id));
		verify(crezamRepository, times(1)).findById(id);
		verify(crezamRepository, times(1)).deleteById(id);
	}

}
