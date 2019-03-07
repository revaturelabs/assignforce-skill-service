package com.revature.assignforce.testing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.containers.SkillsArray;
import com.revature.assignforce.controllers.SkillController;
import com.revature.assignforce.services.SkillService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
public class SkillControllerTest {

	private MockMvc mvc;

	@Mock
	private SkillService skillService;

	@InjectMocks
	private SkillController skillController;

	private JacksonTester<Skill> jsonSkill;
	private JacksonTester<List<Skill>> jsonListSkill;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(skillController)
				.build();
	}

	
	// This function test whether the skills that created are actually saved in the Database
	// and returns all the skills that are saved 
	@Test
	public void getAllTest() throws Exception {
		Skill s1 = new Skill(2, "Spring", true);
		Skill s2 = new Skill(1, "Java", true);
		List<Skill> skillList = new ArrayList<Skill>();
		skillList.add(s1);
		skillList.add(s2);
		given(skillService.getAll()).willReturn(skillList);
		MockHttpServletResponse response = mvc.perform(
				get("/")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonListSkill.write(skillList).getJson());

	}
	
	// This function test whether the getSkillId() returns the correct id associated with the skill
	// object and that the HTTP status is 'ok' if the getSkillId() method corresponds with skill 
	// object's id 
	@Test
	public void getByIdTestOk() throws Exception {
		Skill s1 = new Skill(2, "Spring", true);
		Optional<Skill> op1 = Optional.ofNullable(s1);
		given(skillService.getSkillById(2)).willReturn(op1);
		MockHttpServletResponse response = mvc.perform(
				get("/2")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonSkill.write(op1.get()).getJson());
	}
//
//	// This function test if the HTTP status is set to Response Entity is set to 'NOT_FOUND'
//	// when you try to get a skill Id that is not saved or yet instantiated
	@Test
	public void getByIdTestNotFound() throws Exception {
		given(skillService.getSkillById(13)).willReturn(Optional.ofNullable(null));
		MockHttpServletResponse response = mvc.perform(
				get("/13")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(response.getContentAsString()).isEmpty();
	}
//
//
//	// Add check for same Id and make sure skills cannot have same ID
//	// Currently, skill id can be duplicates and will increase skill size
//	// Also have it so that one cannot make duplicate skill name with different ID.
//
//	// This function test whether the skills that are not null are saved in the Array list and that the
//	// list returns the right number of skill objects that set the HTTP status is set to 'ok'
//	@Test
//	public void getByArrayTest() {
//		Skill s1 = new Skill(2, "Spring", true);
//		Skill s2 = new Skill(1, "Java", true);
//		Skill s3 = new Skill(4, "PHP", false);
//		Skill s4 = new Skill(4, "HTML", false);
//		List<Skill> skillList = new ArrayList<Skill>();
//		skillList.add(s1);
//		skillList.add(s2);
//		skillList.add(s3);
//		skillList.add(s4);
//		SkillsArray sArray = new SkillsArray(skillList);
//		Optional<Skill> op1 = Optional.ofNullable(s1);
//		Mockito.when(skillRepository.findById(2)).thenReturn(op1);
//		Optional<Skill> op2 = Optional.ofNullable(s2);
//		Mockito.when(skillRepository.findById(1)).thenReturn(op2);
//		Optional<Skill> op3 = Optional.ofNullable(s3);
//		Mockito.when(skillRepository.findById(4)).thenReturn(op3);
//		Optional<Skill> op4 = Optional.ofNullable(s4);
//		Mockito.when(skillRepository.findById(4)).thenReturn(op4);
//		ResponseEntity<List<Skill>> reTest = skillController.getByArray(sArray);
//		assertTrue(reTest.getBody().size() == 4 && reTest.getStatusCode() == HttpStatus.OK);
//	}
//
//	// This function test whether the setSkill() saves all the skills to a new list and that the
//	// HTTP status is set to 'ok'
//	@Test
//	public void getByArrayTest2() {
//		Skill s1 = new Skill(7, "C++", true);
//		Skill s2 = new Skill(18, "C#", false);
//		List<Skill> skillSet = new ArrayList<Skill>();
//		skillSet.add(s1);
//		skillSet.add(s2);
//		SkillsArray sArray = new SkillsArray();
//		sArray.setSkills(skillSet);
//		Optional<Skill> op1 = Optional.ofNullable(s1);
//		Mockito.when(skillRepository.findById(7)).thenReturn(op1);
//		Optional<Skill> op2 = Optional.ofNullable(s2);
//		Mockito.when(skillRepository.findById(18)).thenReturn(op2);
//		ResponseEntity<List<Skill>> reTest = skillController.getByArray(sArray);
//		assertTrue(reTest.getBody().size() == 2 && reTest.getStatusCode() == HttpStatus.OK);
//	}
//
//	// This function test to see if the saveNewSkill() method saves the new skill object to the list
//	// and returns the HTTP status 'ok' if true
	@Test
	public void saveNewSkillTestOk() throws Exception {
		Skill s1 = new Skill(0, "C++", true);
		Skill s2 = new Skill(7, "C++", true);
		when(skillService.createSkill(s1)).thenReturn(s2);
		ResponseEntity<Skill> response = skillController.saveNewSkill(s1);
		verify(skillService, times(1)).createSkill(s1);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody()).isEqualTo(s2);
	}
//
//	// This function will test if a skill object that is created is not a duplicate and will not save the
//	// newly created object and will return HTTP status 'NOT_FOUND'
// 	@Test
// 	public void saveNewSkillTestNotFound() {
// 		Skill s1 = new Skill(7, "C#", true);
// 		ResponseEntity<Skill> reTest = skillController.saveNewSkill(s1);
// 		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
// 	}
//
//	// Checked Id 8 to make sure it really was deleted, and it then checked
//	// status code to make sure it was not found.
//
//	// This function will test whether the deleteById() method deletes skill object with the corresponding
//	// id that is being requested to delete
//	@Test
//	public void deleteSkillTest() {
//		Mockito.doNothing().when(skillRepository).deleteById(8);
//		ResponseEntity<Skill> reTest = skillController.deleteSkill(8);
//		ResponseEntity<Skill> reTest2 = skillController.getById(8);
//		assertTrue(reTest2.getStatusCode() == HttpStatus.NOT_FOUND);
//	}
//
//	// This function will test whether the updateSkill() method updates the current skill object that is saved
//	// and will return the HTTP status 'ok' if updated
//	@Test
//	public void updateSkillTestOk() {
//		Skill s1 = new Skill(7, "C++", true);
//		s1.setIsActive(false);
//		Mockito.when(skillRepository.save(s1)).thenReturn(s1);
//		ResponseEntity<Skill> reTest = skillController.updateSkill(s1);
//		assertTrue(reTest.getBody().getIsActive() == false && reTest.getStatusCode() == HttpStatus.OK);
//	}
//
//	// This function will test the updateSkill if you try to update a skill object that does not exist and will
//	// return the HTTP status 'NOT_FOUND'
//	@Test
//	public void updateSkillTestNotFound() {
//		Skill s1 = new Skill(9, "Scala", true);
//		s1.setIsActive(false);
//		ResponseEntity<Skill> reTest = skillController.updateSkill(s1);
//		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
//	}

}
