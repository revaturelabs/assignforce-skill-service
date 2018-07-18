package com.revature.assignforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.TrainerIdHolder;

@Repository
public interface TrainerIdHolderRepository extends JpaRepository<TrainerIdHolder,Integer>{

}
