package info.mhylle.playground.lpr3.model.old;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@XmlRootElement
public class Patient implements DataElement {
    private String id;
    private String alternativeId;
    private String name;
    private List<EpisodeOfCareElement> episodeOfCareElements;

    public Patient() {
        this.id = UUID.randomUUID().toString();
        episodeOfCareElements = new ArrayList<>();
    }

    @XmlElement(name = "Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "AlternativeId")
    public String getAlternativeId() {
        return alternativeId;
    }

    public void setAlternativeId(String alternativeId) {
        this.alternativeId = alternativeId;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "EpisodeOfCareElements")
    public List<EpisodeOfCareElement> getEpisodeOfCareElements() {
        return episodeOfCareElements;
    }

    public void addEpisodeOfCareElement(EpisodeOfCareElement episodeOfCareElement) {
        this.episodeOfCareElements.add(episodeOfCareElement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        return id.equals(patient.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
