package info.mhylle.playground.lpr3.model.SKS.encounter;

public enum StatusCode {
    PLANNED("Planned"),
    ARRIVED("Arrived"),
    INPROGRESS("In-progress"),
    ONLEAVE("On Leave"),
    FINISHED("Finished"),
    CANCELLED("Cancelled");
    private String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
