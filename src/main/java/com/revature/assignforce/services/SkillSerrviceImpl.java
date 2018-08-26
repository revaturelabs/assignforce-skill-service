package com.revature.assignforce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.messaging.messenger.SkillMessenger;
import com.revature.assignforce.repository.SkillRepository;

@Service
@Transactional
public class SkillSerrviceImpl implements SkillService {

	@Autowired
	SkillRepository skillRepo;
	
	@Autowired
	SkillMessenger skillMessenger;

	@Override
	public Optional<Skill> getSkillById(int id) {
		return skillRepo.findById(id);
	}

	@Override
	public List<Skill> getAll() {
		
		return skillRepo.findAll();
	}

	@Override // Create Check for Duplicate skill name. If duplicate, ignore.
	public Skill createSkill(Skill skill) {

		// @Column name
		// @Column id
		// if name || id == new name || new id
		// skip / cancel create skill

		return skillRepo.save(skill);
	}

	@Override
	public Skill updateSkill(Skill skill) {
		skillMessenger.sendDeactivateMessage(skill.getSkillId());
		return skillRepo.save(skill);
	}

	@Override
	public void deleteSkill(int id) {
	    skillMessenger.sendDeletionMessage(id);
		skillRepo.deleteById(id);
		
	}
	
}
