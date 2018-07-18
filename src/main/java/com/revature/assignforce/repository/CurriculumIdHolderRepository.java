package com.revature.assignforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.CurriculumIdHolder;

@Repository
public interface CurriculumIdHolderRepository extends JpaRepository<CurriculumIdHolder,Integer>{
	
}
