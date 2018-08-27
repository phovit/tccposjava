package br.edu.unitri.posjava.tcc.med4you.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Created by edufratari on 01/08/18.
 */
@Entity
public class MedicalPrescription {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne()
	private Doctor doctor;

	private String cid;

	@OneToMany()
	private List<Medicine> medicines;

	private String dosage;

	private Boolean firstDose;

	private String period;

	public MedicalPrescription() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
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

}
