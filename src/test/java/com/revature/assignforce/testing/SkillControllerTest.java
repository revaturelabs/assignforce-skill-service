package com.revature.assignforce.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.containers.SkillsArray;
import com.revature.assignforce.controllers.SkillController;
import com.revature.assignforce.repository.SkillRepository;
import com.revature.assignforce.services.SkillSerrviceImpl;
import com.revature.assignforce.services.SkillService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillControllerTest {

	@Configuration
	static class SkillServiceTestContextConfiguration {
	@Bean
	public SkillService SkillService() {
		return new SkillSerrviceImpl();
		}
	@Bean
	public SkillRepository SkillRepository() {
		return Mockito.mock(SkillRepository.class);
		}
	@Bean
	public SkillController SkillController() {
		return new SkillController();
		}
	}
	
	@Autowired
	private SkillRepository skillRepository;
	@Autowired
	private SkillController skillController;
	
	@Test
	public void getAllTest() {
		Skill s1 = new Skill(2, "Spring", true);
		Skill s2 = new Skill(1, "Java", true);
		List<Skill> skillList = new ArrayList<Skill>();
		skillList.add(s1);
		skillList.add(s2);
		Mockito.when(skillRepository.findAll()).thenReturn(skillList);
		List<Skill> testList = skillController.getAll();
		assertTrue(testList.size() == 2);
	}
	
	@Test
	public void getByIdTestOk() {
		Skill s1 = new Skill(2, "Spring", true);
		Optional<Skill> op1 = Optional.ofNullable(s1);
		Mockito.when(skillRepository.findById(2)).thenReturn(op1);
		ResponseEntity<Skill> reTest = skillController.getById(2);
		assertTrue(reTest.getBody().getSkillId() == 2 && reTest.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void getByIdTestNotFound() {
		ResponseEntity<Skill> reTest = skillController.getById(13);
		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
	}

	// Add check for same Id and make sure skills cannot have same ID
	// Currently, skill id can be duplicates and will increase skill size
	// Also have it so that one cannot make duplicate skill name with different ID.
	@Test
	public void getByArrayTest() {
		Skill s1 = new Skill(2, "Spring", true);
		Skill s2 = new Skill(1, "Java", true);
		Skill s3 = new Skill(4, "PHP", false);
		Skill s4 = new Skill(4, "HTML", false);
		List<Skill> skillList = new ArrayList<Skill>();
		skillList.add(s1);
		skillList.add(s2);
		skillList.add(s3);
		skillList.add(s4);
		SkillsArray sArray = new SkillsArray(skillList);
		Optional<Skill> op1 = Optional.ofNullable(s1);
		Mockito.when(skillRepository.findById(2)).thenReturn(op1);
		Optional<Skill> op2 = Optional.ofNullable(s2);
		Mockito.when(skillRepository.findById(1)).thenReturn(op2);
		Optional<Skill> op3 = Optional.ofNullable(s3);
		Mockito.when(skillRepository.findById(4)).thenReturn(op3);
		Optional<Skill> op4 = Optional.ofNullable(s4);
		Mockito.when(skillRepository.findById(4)).thenReturn(op4);
		ResponseEntity<List<Skill>> reTest = skillController.getByArray(sArray);
		assertTrue(reTest.getBody().size() == 4 && reTest.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void getByArrayTest2() {
		Skill s1 = new Skill(7, "C++", true);
		Skill s2 = new Skill(18, "C#", false);
		List<Skill> skillSet = new ArrayList<Skill>();
		skillSet.add(s1);
		skillSet.add(s2);
		SkillsArray sArray = new SkillsArray();
		sArray.setSkills(skillSet);
		Optional<Skill> op1 = Optional.ofNullable(s1);
		Mockito.when(skillRepository.findById(7)).thenReturn(op1);
		Optional<Skill> op2 = Optional.ofNullable(s2);
		Mockito.when(skillRepository.findById(18)).thenReturn(op2);
		ResponseEntity<List<Skill>> reTest = skillController.getByArray(sArray);
		assertTrue(reTest.getBody().size() == 2 && reTest.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void saveNewSkillTestOk() {
		Skill s1 = new Skill(7, "C++", true);
		Mockito.when(skillRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Skill> reTest = skillController.saveNewSkill(s1);
		assertTrue(reTest.getBody().getSkillId() == 7 && reTest.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void saveNewSkillTestNotFound() {
		Skill s1 = new Skill(7, "C#", true);
		ResponseEntity<Skill> reTest = skillController.saveNewSkill(s1);
		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
	}

	// Checked Id 8 to make sure it really was deleted, and it then checked
	// status code to make sure it was not found.
	@Test
	public void deleteSkillTest() {
		Mockito.doNothing().when(skillRepository).deleteById(8);
		ResponseEntity<Skill> reTest = skillController.deleteSkill(8);
		ResponseEntity<Skill> reTest2 = skillController.getById(8);
		assertTrue(reTest2.getStatusCode() == HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void updateSkillTestOk() {
		Skill s1 = new Skill(7, "C++", true);
		s1.setIsActive(false);
		Mockito.when(skillRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Skill> reTest = skillController.updateSkill(s1);
		assertTrue(reTest.getBody().getIsActive() == false && reTest.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void updateSkillTestNotFound() {
		Skill s1 = new Skill(9, "Scala", true);
		s1.setIsActive(false);
		ResponseEntity<Skill> reTest = skillController.updateSkill(s1);
		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
	}

}
