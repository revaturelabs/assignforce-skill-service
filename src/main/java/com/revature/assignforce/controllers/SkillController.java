package com.revature.assignforce.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContext;

import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.containers.SkillsArray;
import com.revature.assignforce.services.SkillService;

@RestController
public class SkillController {

	Logger logger = LoggerFactory.getLogger(SkillController.class);
	private SkillService skillServ;

	@Autowired
	public SkillController(SkillService skillService) {
		this.skillServ = skillService;
	}



	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology','Trainer','Manager of Technology','Center Head')")
	public List<Skill> getAll() {
		return skillServ.getAll();
	}

	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology','Trainer','Manager of Technology','Center Head')")
	public ResponseEntity<Skill> getById(@PathVariable("id") int id) {
		Optional<Skill> skill = skillServ.getSkillById(id);
		if (!skill.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(skill.get(), HttpStatus.OK);
	}

	@PostMapping(value = "by-array", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology','Trainer','Manager of Technology','Center Head')")
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

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("isAuthenticated() and hasRole('SVP of Technology')")
	public ResponseEntity<Skill> saveNewSkill(@RequestBody @Validated(Skill.New.class) Skill skill) {
		Skill newSkill = skillServ.createSkill(skill);


		if (newSkill != null) {
			return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "{id}")
	@PreAuthorize("isAuthenticated() and hasRole('SVP of Technology')")
	public ResponseEntity<Skill> deleteSkill(@PathVariable("id") int id) {
		skillServ.deleteSkill(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("isAuthenticated() and hasRole('SVP of Technology')")
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
