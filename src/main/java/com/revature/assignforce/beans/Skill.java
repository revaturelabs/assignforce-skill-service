package com.revature.assignforce.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * 
 * A Skill - create table SKILL in DB with column ("SKILL_ID", "SKILLNAME", "IS_ACTIVE")
 * also constructor based on variable used for column's name, getters and setters 
 *
 */
@Component
@Entity
@Table(name="SKILL") // map table SKILL to DB
@EnableSwagger2
public class Skill {

	// Weird Annotations

	@Id //create column SKILL_ID that can't be empty, null and must be positive
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="skill")
	@SequenceGenerator(name="skill", sequenceName="skill_seq", allocationSize=1)
	@Column(name="SKILL_ID") 
	@NotNull(message = "Skill must have id.", groups = {New.class, Existing.class})
	@Range(min = 0, max = 0, message = "New Skill must have id of 0", groups = {New.class})
	@Positive(message = "Existing Skill must have a positive id number", groups = {Existing.class})
	@JsonProperty(value="id")
	private Integer skillId;
	
	@Column(name = "SKILLNAME") //create column SKILLNAME that can't be empty, null and must be has long from 1 to 128 
	@NotEmpty(message = "Skill must not be empty.")
	@NotNull(message = "Skill must have a name.", groups = {New.class, Existing.class})
	@Size(min = 1, max = 128, message = "Skill name must be between 1 and 128 characters", groups = {New.class, Existing.class})
	@JsonProperty(value="name")
	private String skillName;
	
	@Column(name="IS_ACTIVE") //create column IS_ACTIVE that can't be null
	@NotNull(message = "Skill must define whether it is active.", groups = {New.class, Existing.class})
	private Boolean isActive;

	//Validation groups
	public interface Existing {}
	public interface New {}
	
	//constructors
	public Skill() {
		super();
	}

	// parameterized constructor based on variables 
	public Skill(int skillId, String skillName, Boolean isActive) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.isActive = isActive;
	}

	// Getters and Setters
	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}	
}
