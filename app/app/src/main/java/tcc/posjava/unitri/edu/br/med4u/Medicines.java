package tcc.posjava.unitri.edu.br.med4u;

public class Medicines {

    private int id;
    private String name;
    private String indications;
    private String contraindications;
    private String adverseReactions;
    private String precautions;
    private String codebar;
    private String activeIngredients;
    private String msRecord;
    private boolean generic;
    private String image;
    private String originalMedicine;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
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

    public String getCodebar() {
        return codebar;
    }

    public void setCodebar(String codebar) {
        this.codebar = codebar;
    }

    public String getActiveIngredients() {
        return activeIngredients;
    }

    public void setActiveIngredients(String activeIngredients) {
        this.activeIngredients = activeIngredients;
    }

    public String getMsRecord() {
        return msRecord;
    }

    public void setMsRecord(String msRecord) {
        this.msRecord = msRecord;
    }

    public boolean isGeneric() {
        return generic;
    }

    public void setGeneric(boolean generic) {
        this.generic = generic;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOriginalMedicine() {
        return originalMedicine;
    }

    public void setOriginalMedicine(String originalMedicine) {
        this.originalMedicine = originalMedicine;
    }
}
