package com.revature.assignforce.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.assignforce.messaging.messenger.SkillMessenger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.repository.SkillRepository;
import com.revature.assignforce.services.SkillSerrviceImpl;
import com.revature.assignforce.services.SkillService;
import com.revature.assignforce.messaging.messenger.SkillMessenger;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillServiceImplTest {

	// Add this section for application context.
	@Configuration
	static class SkillServiceTestContextConfiguration {
	@Bean
	public SkillService SkillService() { return new SkillSerrviceImpl(); }
	@Bean
	public SkillRepository SkillRepository() {
		return Mockito.mock(SkillRepository.class);
		}
	@Bean
		public SkillMessenger SkillMessenger() { return Mockito.mock(SkillMessenger.class); }
	}
	
	@Autowired
	private SkillService skillService;
	@Autowired
	private SkillRepository skillRepository;
	@Autowired
	private SkillMessenger skillMessenger;

	// This function test whether the getSkillId() returns the correct id associated with the skill
	// object and that the HTTP status is 'ok' if the getSkillId() method corresponds with skill 
	// object's id 
	@Test
	public void getSkillByIdTest() {
		Skill s1 = new Skill(2, "Spring", true);
		Optional<Skill> op1 = Optional.ofNullable(s1);
		Mockito.when(skillRepository.findById(2)).thenReturn(op1);
		Optional<Skill> opTest = skillService.getSkillById(2);
		assertTrue(opTest.get().getSkillId() == 2);
	}
	
	// This function test if the getAll() method gets all the skill objects that are saved in the Database
	@Test
	public void getAllTest() {
		Skill s1 = new Skill(2, "Spring", true);
		Skill s2 = new Skill(1, "Java", true);
		Skill s3 = new Skill(4, "PHP", false);
		List<Skill> skillList = new ArrayList<Skill>();
		skillList.add(s1);
		skillList.add(s2);
		skillList.add(s3);
		Mockito.when(skillRepository.findAll()).thenReturn(skillList);
		List<Skill> testList = skillService.getAll();
		assertTrue(testList.size() == 3);
	}
	
	// 
	@Test
	public void createSkillTest() {
		Skill s1 = new Skill(5, "Hibernate", true);
		Mockito.when(skillRepository.save(s1)).thenReturn(s1);
		Skill testSkill = skillService.createSkill(s1);
		assertTrue(testSkill.getSkillId() == 5);
	}
	
	// This function test to see if the updateSkill() method updates the skill from not active, to
	// active returns false if it does not change 
	@Test
	public void updateSkillTest() {
		Skill s1 = new Skill(5, "Hibernate", true);
		s1.setIsActive(false);
		Mockito.when(skillRepository.save(s1)).thenReturn(s1);
		Skill testSkill = skillService.updateSkill(s1);
		assertFalse(testSkill.getIsActive());
	}

	// This function test to see if the deleteById() method deletes the associated skill obeject with the 
	// corresponding id, returns false if it does not delete
	@Test
	public void deleteSkillTest() {
		Mockito.doNothing().when(skillRepository).deleteById(7);
		skillService.deleteSkill(7);
		Optional<Skill> opTest = skillService.getSkillById(7);
		assertFalse(opTest.isPresent());
	}

}
