package com.revature.assignforce.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TRAINERIDHOLDER")
public class TrainerIdHolder {
	
	@Id
	@Column(name="TRAINERID")
	private int trainerId;

	public TrainerIdHolder() {
		super();
	}

	public TrainerIdHolder(int trainerId) {
		super();
		this.trainerId = trainerId;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}
	
	
}
