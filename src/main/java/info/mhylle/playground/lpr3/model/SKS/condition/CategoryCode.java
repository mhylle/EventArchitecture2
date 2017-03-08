package info.mhylle.playground.lpr3.model.SKS.condition;

public enum CategoryCode {
    COMPLAINT("Complaint"),
    SYMPTOM("Symptom"),
    FINDING("Finding"),
    DIAGNOSIS("Diagnosis");
    private String code;

    CategoryCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
