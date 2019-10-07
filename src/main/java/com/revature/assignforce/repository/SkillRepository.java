package com.revature.assignforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Skill;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Repository
@EnableSwagger2
// Extends SkillRepository from JpaRepository based on parameters
public interface SkillRepository extends JpaRepository<Skill, Integer>{
}
