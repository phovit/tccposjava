package br.edu.unitri.posjava.tcc.med4you.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
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
    
    @OneToMany
    private List<Indication> indications;
    
    @OneToMany
    private List<AgainstIndication> contraindications;
    
    @OneToMany
    private List<AdverseReactions> adverseReactions;
    
    @OneToMany
    private List<Precaution> precautions;
    
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

    public List<Indication> getIndications() {
		return indications;
	}

	public void setIndications(List<Indication> indications) {
		this.indications = indications;
	}

	public List<AgainstIndication> getContraindications() {
		return contraindications;
	}

	public void setContraindications(List<AgainstIndication> contraindications) {
		this.contraindications = contraindications;
	}

    public List<AdverseReactions> getAdverseReactions() {
		return adverseReactions;
	}

	public void setAdverseReactions(List<AdverseReactions> adverseReactions) {
		this.adverseReactions = adverseReactions;
	}

	public List<Precaution> getPrecautions() {
		return precautions;
	}

	public void setPrecautions(List<Precaution> precautions) {
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
