package com.crezam.endpoints.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class JobPosting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titile;
	private String description;
	private String location;
	private String company;
	private String salaryRange;
	private List<String> requiredSkills;
	private LocalDate applicationDeadline;
	
	public JobPosting() {
		
	}
	public JobPosting(String titile, String description, String location, String company, String salaryRange,
			List<String> requiredSkills, LocalDate applicationDeadline) {
		this.titile = titile;
		this.description = description;
		this.location = location;
		this.company = company;
		this.salaryRange = salaryRange;
		this.requiredSkills = requiredSkills;
		this.applicationDeadline = applicationDeadline;
	}
	
	

}
