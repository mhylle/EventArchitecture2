package info.mhylle.playground.lpr3.model;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import java.util.UUID;

public class Condition {

    private UUID id;
    @NotNull
    private UUID patient;
    private Encounter encounter;
    private Period period;

    public Condition() {
        id = UUID.randomUUID();
    }

    @XmlElement(name = "Id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
