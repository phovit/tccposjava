package br.edu.unitri.posjava.tcc.med4you.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * Created by pauloho on 14/09/18.
 */
@Entity
public class MedicalPrescriptionItem {

    @Id
    @GeneratedValue
    private Long id;


    @OneToOne
    private Medicine medicine;

    private String dosage;

    private Date firstDose;

    @OneToOne
    private User responsableUser;

    private String observation;


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

    public Date getFirstDose() {
        return firstDose;
    }

    public void setFirstDose(Date firstDose) {
        this.firstDose = firstDose;
    }

    public User getResponsableUser() {
        return responsableUser;
    }

    public void setResponsableUser(User responsableUser) {
        this.responsableUser = responsableUser;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
