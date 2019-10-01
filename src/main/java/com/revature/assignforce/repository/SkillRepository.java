package com.revature.assignforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangofactory.swagger.plugin.EnableSwagger;
import com.revature.assignforce.beans.Skill;

@Repository
@EnableSwagger
// Extends SkillRepository from JpaRepository based on parameters
public interface SkillRepository extends JpaRepository<Skill, Integer>{
}
