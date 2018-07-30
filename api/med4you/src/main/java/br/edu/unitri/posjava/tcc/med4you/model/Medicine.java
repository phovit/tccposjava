package br.edu.unitri.posjava.tcc.med4you.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * Created by pauloho on 18/07/18.
 */
@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String indication;
    private String contraindication;
    private String adverseReactions;
    private String precautions;
    private Long codebar;
    private String activeIngredients;
    private Long msRecord;
    private boolean generic;
    @Lob
    @Column(name = "IMAGE", nullable = true, columnDefinition = "mediumblob")
    private byte[] image;


    @OneToOne
    @JoinColumn(name = "ORIGINAL_MEDICINE_ID", referencedColumnName = "ID")
    private Medicine originalMedicine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getContraindication() {
        return contraindication;
    }

    public void setContraindication(String contraindication) {
        this.contraindication = contraindication;
    }

    public String getAdverseReactions() {
        return adverseReactions;
    }

    public void setAdverseReactions(String adverseReactions) {
        this.adverseReactions = adverseReactions;
    }

    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }

    public Long getCodebar() {
        return codebar;
    }

    public void setCodebar(Long codebar) {
        this.codebar = codebar;
    }

    public String getActiveIngredients() {
        return activeIngredients;
    }

    public void setActiveIngredients(String activeIngredients) {
        this.activeIngredients = activeIngredients;
    }

    public Long getMsRecord() {
        return msRecord;
    }

    public void setMsRecord(Long msRecord) {
        this.msRecord = msRecord;
    }

    public boolean isGeneric() {
        return generic;
    }

    public void setGeneric(boolean generic) {
        this.generic = generic;
    }

    public Medicine getOriginalMedicine() {
        return originalMedicine;
    }

    public void setOriginalMedicine(Medicine originalMedicine) {
        this.originalMedicine = originalMedicine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
