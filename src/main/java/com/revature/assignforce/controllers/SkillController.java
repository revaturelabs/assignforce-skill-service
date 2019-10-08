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

import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.containers.SkillsArray;
import com.revature.assignforce.services.SkillService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * 
 * A controller for retrieving, creating, updating and deleting skills information
 *
 */
@RestController
@EnableSwagger2
@Api(value = "SkillController")
public class SkillController {

	Logger logger = LoggerFactory.getLogger(SkillController.class);

	@Autowired
	private SkillService skillServ;

	/**
	 * map get all skills by type based on settings APPLICATION_JSON_VALUE
	 * 
	 * @return A List of All Skills
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get All Skills", response = Skill.class, responseContainer = "List", tags = "SkillController", nickname = "getAllSkills")
	public List<Skill> getAll() {

		return skillServ.getAll();
	}

	/**
	 * @param id Skill id
	 * @return just one Skill
	 * @see Skill
	 * @see ResponseEntity
	 */
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Skill Information by Id", response = ResponseEntity.class, tags = "SkillController", nickname = "getSkillById")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = Skill.class) })
	public ResponseEntity<Skill> getById(@PathVariable("id") int id) {
		Optional<Skill> skill = skillServ.getSkillById(id);
		if (!skill.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(skill.get(), HttpStatus.OK);
	}

	/**
	 * Get optional skills by id of skill array
	 * 
	 * @param arr Accepts SkillsArray as parameter
	 * @return optional Skill from Array of Skills
	 * @see Skill
	 */
	@PostMapping(value = "by-array", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get optional skills by id of skill array", tags = "SkillController", nickname = "getSkills")
	@ApiResponse(code = 200, message = "OK", response = Skill.class, responseContainer = "List")
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

	/**
	 * Create new skill in skill array
	 * 
	 * @param skill New skill
	 * @return status created if Skill was created / bad request
	 * @see Skill
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add New Skill", response = ResponseEntity.class, tags = "SkillController", nickname = "saveNewSkill")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 201, message = "Created", response = Skill.class) })
	public ResponseEntity<Skill> saveNewSkill(@RequestBody @Validated(Skill.New.class) Skill skill) {
		Skill newSkill = skillServ.createSkill(skill);
		if (newSkill != null) {
			return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Delete skill by id
	 * 
	 * @param id A Skill/ Id of object to be deleted
	 * @return A Skill ResponseEntity
	 * @see Skill
	 */
	@DeleteMapping(value = "{id}")
	@ApiOperation(value = "Delete Skill Information", tags = "SkillController", nickname = "deleteSkill")
	@ApiResponse(code = 200, message = "OK", response = Skill.class)
	public ResponseEntity<Skill> deleteSkill(@PathVariable("id") int id) {
		skillServ.deleteSkill(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Update skill based on skill name
	 * 
	 * @param skill Updated Skill Information
	 * @return A Skill ResponseEntity status OK / NOT FOUND
	 * @see Skill
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Skill Information", response = ResponseEntity.class, tags = "SkillController", nickname = "updateSkill")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = Skill.class) })
	public ResponseEntity<Skill> updateSkill(@RequestBody @Validated(Skill.Existing.class) Skill skill) {
		logger.info("Updating skill ", skill.getSkillName());

		// we are going to assume that we are only deactivating
		// the skill for now
		Skill newSkill = skillServ.updateSkill(skill);
		if (newSkill != null) {
			return new ResponseEntity<>(newSkill, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
