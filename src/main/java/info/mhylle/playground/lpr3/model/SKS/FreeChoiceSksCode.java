package info.mhylle.playground.lpr3.model.SKS;

/**
 * Created by mnh on 07-02-2017.
 */
public enum FreeChoiceSksCode {
    ALDB00("Inget fritvalg"),
    ALDB01("frit sygehusvalg"),
    ALDB02("udvidet frit sygehusvalg");

    private String code;

    FreeChoiceSksCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
