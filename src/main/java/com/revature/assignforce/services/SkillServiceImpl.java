package com.revature.assignforce.services;

import java.util.List;
import java.util.Optional;

import com.revature.assignforce.SkillsNotifierBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.repository.SkillRepository;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {
	private static String name = "Skill";
	private static final Logger LOG = LoggerFactory.getLogger(name);

	@Autowired
	SkillRepository skillRepo;

	SkillsSNSNotificationSender notificationSender;

	@Autowired
	public void setNotificationSender(SkillsSNSNotificationSender notificationSender) {
		this.notificationSender = notificationSender;
	}

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
		Skill s = skillRepo.save(skill);
		LOG.info("Pushing message for adding skill {}", skill.getSkillId());
		notificationSender.sendAddNotification(new SkillsNotifierBean(s.getSkillId()));
		return s;

	}

	@Override
	public Skill updateSkill(Skill skill) {
		LOG.info("Pushing message for deleting skill {}", skill.getSkillId());
		notificationSender.sendDeleteNotification(new SkillsNotifierBean(skill.getSkillId()));
		return skillRepo.save(skill);
	}

	@Override
	public void deleteSkill(int id) {
		skillRepo.deleteById(id);
	}
	
}
