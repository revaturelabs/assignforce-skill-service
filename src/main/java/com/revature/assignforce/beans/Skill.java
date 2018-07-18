package com.revature.assignforce.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="SKILL")
public class Skill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="skill")
	@SequenceGenerator(name="skill", sequenceName="skill_seq", allocationSize=1)
	@Column(name="SKILL_ID")
	private int id;
	
	@Column(name = "SKILLNAME") 
	private String skillName;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="TRAINER_SKILLS",
			joinColumns=@JoinColumn(name="SKILL_ID"),
			inverseJoinColumns=@JoinColumn(name="TRAINERID"))
	private Set<TrainerIdHolder> trainers;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="CURRICULUM_SKILLS",
			joinColumns=@JoinColumn(name="SKILL_ID"),
			inverseJoinColumns=@JoinColumn(name="CURRICULUMID"))
	private Set<CurriculumIdHolder> curricula;
	
	@Column(name="IS_ACTIVE")
	private Boolean isActive;

	//constructors
	public Skill() {
		super();
	}

	public Skill(int id, String name, Set<TrainerIdHolder> trainers, Set<CurriculumIdHolder> curricula) {
		super();
		this.id = id;
		this.skillName = name;
		this.trainers = trainers;
		this.curricula = curricula;
	}

	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public Set<TrainerIdHolder> getTrainers() {
		return trainers;
	}

	public void setTrainers(Set<TrainerIdHolder> trainers) {
		this.trainers = trainers;
	}

	public Set<CurriculumIdHolder> getCurricula() {
		return curricula;
	}

	public void setCurricula(Set<CurriculumIdHolder> curricula) {
		this.curricula = curricula;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
