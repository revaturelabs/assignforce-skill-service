package com.revature.assignforce.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.containers.SkillsArray;
import com.revature.assignforce.services.SkillService;

@RestController
public class SkillController {

	@Autowired
	private SkillService skillServ;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Skill> getAll() {
		
		return skillServ.getAll();
	}

	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Skill> getById(@PathVariable("id") int id) {
		Optional<Skill> skill = skillServ.getSkillById(id);
		if (!skill.isPresent())
			return new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Skill>(skill.get(), HttpStatus.OK);
	}

	@PostMapping(value = "by-array", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Skill>> getByArray(@RequestBody SkillsArray arr) {
		List<Skill> skills = new ArrayList<Skill>();
		for (Integer id : arr.getSkills()) {
			Optional<Skill> temp = skillServ.getSkillById(id);
			if (temp.isPresent()) {
				skills.add(temp.get());
			}
		}
		return new ResponseEntity<List<Skill>>(skills, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Skill> saveNewSkill(@RequestBody Skill skill) {
		Skill newSkill = skillServ.createSkill(skill);

		if (newSkill != null) {
			return new ResponseEntity<Skill>(newSkill, HttpStatus.OK);
		} else {
			return new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Skill> deleteSkill(@PathVariable("id") int id) {
		skillServ.deleteSkill(id);
		return new ResponseEntity<Skill>(HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill) {
		Skill newSkill = skillServ.createSkill(skill);

		if (newSkill != null) {
			return new ResponseEntity<Skill>(newSkill, HttpStatus.OK);
		} else {
			return new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
		}
	}
}
