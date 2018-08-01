package com.revature.assignforce.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="SKILL")
public class Skill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="skill")
	@SequenceGenerator(name="skill", sequenceName="skill_seq", allocationSize=1)
	@Column(name="SKILL_ID")
	@Null(message = "New Skill must not have id.", groups = New.class)
	@NotNull(message = "Existing Skill must have id.", groups = Existing.class)
	private int skillId;
	
	@Column(name = "SKILLNAME")
	@NotNull(message = "Skill must have a name.", groups = {New.class, Existing.class})
	@NotBlank(message = "Skill name must not be blank.", groups = {New.class, Existing.class})
	@Size(min = 1, max = 128, message = "Skill name must be between 1 and 128 characters", groups = {New.class, Existing.class})
	private String skillName;
	
	@Column(name="IS_ACTIVE")
	@NotNull(message = "Skill must define whether it is active.", groups = {New.class, Existing.class})
	private Boolean isActive;

	//Validation groups
	public interface Existing {}
	public interface New {}
	
	//constructors
	public Skill() {
		super();
	}

	public Skill(int skillId, String skillName, Boolean isActive) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.isActive = isActive;
	}

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
