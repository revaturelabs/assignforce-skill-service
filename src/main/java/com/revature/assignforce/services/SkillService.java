package com.revature.assignforce.services;

import java.util.List;
import java.util.Optional;

import com.mangofactory.swagger.plugin.EnableSwagger;
import com.revature.assignforce.beans.Skill;

@EnableSwagger

// DAO that consist list of method used in DAOImpl 
public interface SkillService {
	public List<Skill> getAll(); // get all skill 
	public Optional<Skill> getSkillById(int id); // get skill by id	
	public Skill createSkill(Skill skill); // create skill 
	public Skill updateSkill(Skill skill); // update skill	
	public void deleteSkill(int id); // delete skill by id
}
