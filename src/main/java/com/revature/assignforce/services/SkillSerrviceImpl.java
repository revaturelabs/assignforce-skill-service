package com.revature.assignforce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.repository.SkillRepository;
import com.revature.messaging.producer.SkillMessenger;

@Service
@Transactional
public class SkillSerrviceImpl implements SkillService {

	@Autowired
	SkillRepository skillRepo;
	
	// @Autowired
	SkillMessenger skillMessenger;

	@Override
	public Optional<Skill> getSkillById(int id) {
		return skillRepo.findById(id);
	}

	@Override
	public List<Skill> getAll() {
		
		return skillRepo.findAll();
	}

	@Override
	public Skill createSkill(Skill skill) {
		return skillRepo.save(skill);
	}

	@Override
	public Skill updateSkill(Skill skill) {
		return skillRepo.save(skill);
	}

	@Override
	public void deleteSkill(int id) {
	    skillMessenger.sendDeletionMessage(id);
		skillRepo.deleteById(id);
		
	}
	
}
