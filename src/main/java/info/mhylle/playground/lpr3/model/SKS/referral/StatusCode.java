package info.mhylle.playground.lpr3.model.SKS.referral;

/**
 * Created by mnh on 25-01-2017.
 */
public enum StatusCode {
    DRAFT("Draft"),
    REQUESTED("Requested"),
    ACTIVE("Active"),
    CANCELLED("Cancelled"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected");

    private String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}