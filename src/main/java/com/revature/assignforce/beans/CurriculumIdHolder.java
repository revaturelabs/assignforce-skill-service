package com.revature.assignforce.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="CURRICULUMIDHOLDER")
public class CurriculumIdHolder {
	
	@Id
	@Column(name="CURRICULUMID")
	private int id;

	public CurriculumIdHolder() {
		super();
	}

	public CurriculumIdHolder(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
