package com.revature.assignforce.services;

import java.util.List;
import java.util.Optional;

import com.revature.assignforce.beans.Skill;
import org.springframework.security.access.annotation.Secured;

public interface SkillService {
	public List<Skill> getAll();

	public Optional<Skill> getSkillById(int id);
	public Skill createSkill(Skill skill);
	public Skill updateSkill(Skill skill);
	public void deleteSkill(int id);
}
