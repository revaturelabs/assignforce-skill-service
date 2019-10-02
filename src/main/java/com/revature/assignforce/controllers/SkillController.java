package com.revature.assignforce.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.containers.SkillsArray;
import com.revature.assignforce.services.SkillService;
import com.wordnik.swagger.annotations.Api;

/*
 * 
 * A controller for retrieving, creating, updating and deleting skills information
 *
 */
@RestController
@EnableSwagger
@Api(value="skills-data", description="Operations define skills while create a portfolio")
public class SkillController {

	Logger logger = LoggerFactory.getLogger(SkillController.class);

	@Autowired
	private SkillService skillServ;
	
	/* 
	 * @return	A List of All Skills
	 * @see		All Skills
	 */

	// map get all skills by type based on settings APPLICATION_JSON_VALUE
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)	
	public List<Skill> getAll() {
		
		return skillServ.getAll();
	}
	
	/* 
	 * @return	just one Skill
	 * @see		Skill
	 * @see		ResponseEntity
	 */

	// get skill by id
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Skill> getById(@PathVariable("id") int id) {
		Optional<Skill> skill = skillServ.getSkillById(id);
		if (!skill.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(skill.get(), HttpStatus.OK);
	}
	
	/*	  
	 * @return	optional Skill from Array of Skills
	 * @see		Skill
	 * @see		ResponseEntity
	 */

	// get optional skill by id from skill array
	@PostMapping(value = "by-array", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Skill>> getByArray(@RequestBody @Valid SkillsArray arr) {
		List<Skill> skills = new ArrayList<>();
		for (Skill skl : arr.getSkills()) {
			Optional<Skill> temp = skillServ.getSkillById(skl.getSkillId());
			if (temp.isPresent()) {
				skills.add(temp.get());
			}
		}
		return new ResponseEntity<>(skills, HttpStatus.OK);
	}
	
	/* 
	 * @return	status created if Skill was created / bad request 
	 * @see		Skill
	 * @see		ResponseEntity
	 */

	// create new skill in skill array 
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Skill> saveNewSkill(@RequestBody @Validated(Skill.New.class) Skill skill) {
		Skill newSkill = skillServ.createSkill(skill);
		if (newSkill != null) {
			return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/* 
	 * @param 	id	A Skill/ Id of object to be deleted
	 * @return	A Skill ResponseEntity
	 * @see		Skill
	 * @see		ResponseEntity
	 */

	// delete skill by id
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Skill> deleteSkill(@PathVariable("id") int id) {
		skillServ.deleteSkill(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/* 
	 * @param 	Existing Skill
	 * @return	A Skill ResponseEntity status OK / NOT FOUND
	 * @see		Skill
	 * @see		ResponseEntity
	 */

	// update skill based on skill name
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Skill> updateSkill(@RequestBody @Validated(Skill.Existing.class) Skill skill) {
		logger.info("Updating skill ", skill.getSkillName());

		//we are going to assume that we are only deactivating
		//the skill for now
		Skill newSkill = skillServ.updateSkill(skill);
		if (newSkill != null) {
			return new ResponseEntity<>(newSkill, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
