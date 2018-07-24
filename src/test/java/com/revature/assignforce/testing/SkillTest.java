package com.revature.assignforce.testing;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.repository.SkillRepository;
import com.revature.assignforce.services.SkillSerrviceImpl;
import com.revature.assignforce.services.SkillService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillTest {

	@Configuration
	static class SkillTestContextConfiguration {
	@Bean
	public Skill Skill() {
		return new Skill();
		}
	}
	
	@Test
	public void skillTest1() {
		Skill s1 = new Skill();
		assertNotNull(s1);
	}
	
	@Test
	public void skillTest2() {
		Skill s1 = new Skill(3, "Swift", true);
		assertTrue(s1.getSkillId() == 3);
	}
	
	@Test
	public void getSetIdTest() {
		Skill s1 = new Skill();
		s1.setSkillId(11);
		assertTrue(s1.getSkillId() == 11);
	}
	
	@Test
	public void getSetNameTest() {
		Skill s1 = new Skill();
		s1.setSkillName("JavaScript");
		assertTrue(s1.getSkillName().equals("JavaScript"));
	}
	
	@Test
	public void getSetIsActiveTest() {
		Skill s1 = new Skill();
		s1.setIsActive(true);
		assertTrue(s1.getIsActive());
	}

}
