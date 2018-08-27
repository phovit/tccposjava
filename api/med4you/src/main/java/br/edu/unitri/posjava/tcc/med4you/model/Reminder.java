package br.edu.unitri.posjava.tcc.med4you.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by edufratari on 31/07/18.
 */
@Entity
public class Reminder {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Medicine medicine;
	
	private String dosage;
	
	private Boolean firstDose;
	
	private String period;
	
	private String responsableUser;
	
	private String observation;

	public Reminder() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public Boolean getFirstDose() {
		return firstDose;
	}

	public void setFirstDose(Boolean firstDose) {
		this.firstDose = firstDose;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getResponsableUser() {
		return responsableUser;
	}

	public void setResponsableUser(String responsableUser) {
		this.responsableUser = responsableUser;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
}
