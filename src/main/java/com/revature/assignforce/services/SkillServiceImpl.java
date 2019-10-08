package com.revature.assignforce.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.revature.assignforce.SkillsNotifierBean;
import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.repository.SkillRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * 
 * DAOIml with list of methods:
 *  - get all skills
 *  - get skill by id (optional)
 *  - create skill 
 *  - update skill
 *  - delete skill by id 
 *
 */

@Service
@Transactional
/*
 * Only an user with an SVP role should have access 
 * to these methods.
 */
//@PreAuthorize("hasRole('SVP')")

@EnableSwagger2
public class SkillServiceImpl implements SkillService {
	private static String name = "Skill";
	private static final Logger LOG = LoggerFactory.getLogger(name);

	@Autowired
	SkillRepository skillRepo;
	
	/*
	 * 
	 * Instantiate notification service from Amazon SNS
	 */

	SkillsSNSNotificationSender notificationSender;

	@Autowired
	public void setNotificationSender(SkillsSNSNotificationSender notificationSender) {
		this.notificationSender = notificationSender;
	}
	
	/*
	 * @return skill by id
	 */

	@Override
	public Optional<Skill> getSkillById(int id) {
		return skillRepo.findById(id);
	}

	/*
	 * @return all skills as list
	 */

	@Override
	public List<Skill> getAll() {
		
		return skillRepo.findAll();
	}

	/*
	 * @see Using Amazon SNS to add message to skill
	 * @return message added to skill
	 * 
	 */

	@Override // Create Check for Duplicate skill name. If duplicate, ignore.
	public Skill createSkill(Skill skill) {
		Skill s = skillRepo.save(skill);
		notificationSender.sendAddNotification(new SkillsNotifierBean(s.getSkillId()));
		return s;

	}

	/*
	 * @see Using Amazon SNS to update message on skill
	 * @return updated message of skill
	 * 
	 */

	@Override
	public Skill updateSkill(Skill skill) {
		LOG.info("Pushing message for deleting skill {}", skill.getSkillId());
		notificationSender.sendDeleteNotification(new SkillsNotifierBean(skill.getSkillId()));
		return skillRepo.save(skill);
	}

	/*
	 * @see skill by id and delete it
	 */

	@Override
	public void deleteSkill(int id) {
		skillRepo.deleteById(id);
	}
	
}
