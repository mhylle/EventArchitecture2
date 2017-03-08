package info.mhylle.playground.lpr3.model.old;

import info.mhylle.playground.lpr3.model.SKS.SksCode;

import javax.xml.bind.annotation.XmlElement;
import java.util.UUID;

/**
 * Created by mnh on 25-01-2017.
 */
public class Diagnose implements DataElement {
    private UUID id;
    private SksCode type;
    private SksCode code;
    private SksCode alternative;
    private boolean laterRemoval;

    public Diagnose() {
        id = UUID.randomUUID();
    }

    @XmlElement(name = "Id")
    public String getId() {
        return id.toString();
    }

    public SksCode getType() {
        return type;
    }

    public void setType(SksCode type) {
        this.type = type;
    }

    public SksCode getCode() {
        return code;
    }

    public void setCode(SksCode code) {
        this.code = code;
    }

    public SksCode getAlternative() {
        return alternative;
    }

    public void setAlternative(SksCode alternative) {
        this.alternative = alternative;
    }

    public boolean isLaterRemoval() {
        return laterRemoval;
    }

    public void setLaterRemoval(boolean laterRemoval) {
        this.laterRemoval = laterRemoval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Diagnose diagnose = (Diagnose) o;

        return id.equals(diagnose.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
