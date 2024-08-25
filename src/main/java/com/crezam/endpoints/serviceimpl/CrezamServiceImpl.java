package com.crezam.endpoints.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crezam.endpoints.entity.JobPosting;
import com.crezam.endpoints.repositoy.CrezamRepository;
import com.crezam.endpoints.service.CrezamService;

@Service
public class CrezamServiceImpl implements CrezamService {
	CrezamRepository crezamRepository;

	// Constructor Injection
	public CrezamServiceImpl(CrezamRepository crezamRepository) {
		this.crezamRepository = crezamRepository;
	}

	@Override
	public List<JobPosting> getAllJobPostings() {
		List<JobPosting> all = crezamRepository.findAll();
		return all;
	}

	@Override
	public JobPosting getJobPostingById(Long id) throws Exception {

		JobPosting orElse = crezamRepository.findById(id).orElse(null);
		if (orElse != null) {
			return orElse;
		}

		throw new Exception();
	}

	@Override
	public JobPosting createNewJobPosting(JobPosting jobPosting) throws Exception {
		if (jobPosting != null) {
			JobPosting save = crezamRepository.save(jobPosting);
			return save;
		}

		throw new Exception();
	}

	@Override
	public JobPosting updateJobPostingById(Long id, JobPosting jobPosting) throws Exception {
		if (crezamRepository.existsById(id)) {
			JobPosting existingJob = crezamRepository.findById(id).orElseThrow(() -> new Exception("No Book Id"));
			existingJob.setTitile(jobPosting.getTitile());
			existingJob.setDescription(jobPosting.getDescription());
			existingJob.setLocation(jobPosting.getLocation());
			existingJob.setCompany(jobPosting.getCompany());
			existingJob.setSalaryRange(jobPosting.getSalaryRange());
			existingJob.setRequiredSkills(jobPosting.getRequiredSkills());
			existingJob.setApplicationDeadline(jobPosting.getApplicationDeadline());

			return crezamRepository.save(existingJob);
		} else {
			throw new Exception();
		}
	}

	@Override
	public boolean deleteJobPostingById(Long id) throws Exception {
		Optional<JobPosting> findById = crezamRepository.findById(id);
		if (findById.isPresent()) {
			crezamRepository.deleteById(id);
			return true;
		}
		throw new Exception();

	}

}
