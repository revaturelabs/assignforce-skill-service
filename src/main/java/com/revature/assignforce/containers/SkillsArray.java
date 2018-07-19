package com.revature.assignforce.containers;

import java.util.ArrayList;

import com.revature.assignforce.beans.Skill;

public class SkillsArray {
	ArrayList<Skill> skills;

	public SkillsArray() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SkillsArray(ArrayList<Skill> skills) {
		super();
		this.skills = skills;
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}	
	
}
