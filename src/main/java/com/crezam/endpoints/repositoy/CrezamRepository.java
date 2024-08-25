package com.crezam.endpoints.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crezam.endpoints.entity.JobPosting;


public interface CrezamRepository extends JpaRepository<JobPosting, Long> {

}
