package com.revature.assignforce.revpro;

import com.revature.assignforce.beans.Skill;

import java.util.List;
import java.util.Optional;

/**
 * @author Ella Rozenblum
 *
 * This Class is a prepared skeleton (no functionality) in anticipation for the integration of AssignForce into Revature Pro. 
 * The bellow methods are guidelines to help you identify the intended functionality we had in mind. Your batch may have a
 * shift in purpose by the time this project falls into your hands.
 *
 * The purpose of this class is to house methods for either retrieving and/or modifying information from the Revature Pro API.
 * Each method needs to handle both the communication with the Revature Pro API and the conversion of the data to 
 * AssignForce.
 *
 * Keep in mind that it is important that the information originating from Revature Pro needs to be identifiable as such and not
 * confused with information that is originating from within AssignForce. 
 */
public class SkillRevPro {

	/**
	 * The purpose of this method is to return an optional value that corresponds to a specific skill. It should send a GET request
	 * and convert the response to a Skill object, preferably using Jackson or GSON. You should take advantage of the Optional type 
	 * to check if the skill is actually found. The isPresent() method will tell you if the object is null or not and the get() 
	 * method will actually return the value.
	 * 	 
	 * @param id: this parameter is the id corresponding to the specific skill that needs to be retrieved.
	 * @return: this method returns an Optional value depending on whether the specific skill was found. 
	 */	
	public Optional<Skill> getSkillById(int id) {
		Object skill;
		//send an HTTP request to endpoint for skill by ID
		//set object of Skill to the HTTP response
		//return Skill
		return null;
	}

	/**
	 * The purpose of this method is to return a list of all skills. It should send a GET request and convert the response 
	 * to a Skill list, preferably using Jackson or GSON. 
	 * 
	 * @return: this method returns a list containing all of the elements in the SKill list.
	 */	
	public List<Skill> getAll() {
		List<Skill> allSkills = new List<Skill>();// creating new Skill List
		//Send an HTTP request to endpoint to grab all skills
		//assigning HTTP Response to allSkills List
		//return List of allSkills
		return null;
		
	}
	/**
	 * The purpose of this method is to create and save a new Skill object. It should send a POST request 
	 * to insert the new Skill object into the Revature Pro database. 
	 * 
	 * @param skill: this parameter is the Skill object/record that needs to be created and saved.
	 * @return: this method returns a newly created Skill object. 
	 */	
	// Create Check for Duplicate skill name. If duplicate, ignore.
	public Skill createSkill(Skill skill) {
		//save a new Skill
		//return the new skill object into the endpoint to insert new Skill into RevPro database		
		return null;

	}

	/**
	 * The purpose of this method is to update and save a new Skill object. It should send a PUT request 
	 * with the updated record to the Revature Pro database.
	 * 
	 * @param skill: this parameter is the Skill object/record that needs to be updated and saved.
	 * @return: this method returns an updated SKill object.
	 */
	public Skill updateSkill(Skill skill) {
		//take current Skill record 
		//send updated record to RevPro database
		return null;
	}

	
	/**
	 * The purpose of this method is to delete a Skill object/record by ID. It should send a DELETE request 
	 * to delete the Skill object from the Revature Pro database. 
	 * 
	 * @param id: this parameter is the id corresponding to the specific skill that needs to be deleted.
	 */
	public void deleteSkill(int id) {
		//delete from RevPro database	
		}

}
