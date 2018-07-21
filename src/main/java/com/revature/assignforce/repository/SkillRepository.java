package com.revature.assignforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>{
}
