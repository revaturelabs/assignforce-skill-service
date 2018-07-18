package com.revature.assignforce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>{

	public List<Skill> findByTrainers(int id);
	public List<Skill> findByCurricula(int id);
}
