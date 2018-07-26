package com.revature.assignforce.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Skill;

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
	
    @Test
    public void testSkillWithNoValues() {
        Skill s1 = new Skill();

        // validate the input
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Skill>> violations = validator.validate(s1);
        assertEquals(3, violations.size());
    }
    
    @Test
    public void testValidSkill() {
    	Skill s1 = new Skill(3, "Swift", true);
    	
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	Validator validator = factory.getValidator();
    	Set<ConstraintViolation<Skill>> violations = validator.validate(s1);
    	assertEquals(0, violations.size());
    }
}
