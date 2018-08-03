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
import com.revature.assignforce.beans.Skill.Existing;
import com.revature.assignforce.beans.Skill.New;

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
        Set<ConstraintViolation<Skill>> violations = validator.validate(s1, New.class, Existing.class);
        assertEquals(3, violations.size());
    }
    
    @Test
    public void testValidExistingSkill() {
    	Skill s1 = new Skill(3, "Swift", true);
    	
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	Validator validator = factory.getValidator();
    	Set<ConstraintViolation<Skill>> violations = validator.validate(s1, Existing.class);
    	assertEquals(0, violations.size());
    }
    
    @Test
    public void testValidNewSkill() {
    	Skill s1 = new Skill();
    	s1.setSkillName("Swift");
    	s1.setIsActive(true);
    	
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	Validator validator = factory.getValidator();
    	Set<ConstraintViolation<Skill>> violations = validator.validate(s1, New.class);
    	assertEquals(0, violations.size());
    }
    
    @Test
    public void testEmptyName() {
    	Skill s1 = new Skill(3, "", true);
    	
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	Validator validator = factory.getValidator();
    	Set<ConstraintViolation<Skill>> violations = validator.validate(s1, Existing.class);
    	assertEquals(1, violations.size());
    }
    
    @Test
    public void testExcessivelyLargeName() {
    	//Generating an excessively large string
    	StringBuilder namebuilder = new StringBuilder();
    	for (int i = 0; i < 200; i++) {
    		namebuilder.append("a");
    	}
    	
    	String name = namebuilder.toString();
    	Skill s1 = new Skill(3, name, true);
    	
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	Validator validator = factory.getValidator();
    	Set<ConstraintViolation<Skill>> violations = validator.validate(s1, Existing.class);
    	assertEquals(1, violations.size());
    }
}
