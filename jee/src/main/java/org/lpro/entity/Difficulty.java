package org.lpro.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.TreeSet;
import java.util.Set;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name="Difficulty.findAll",query="SELECT d FROM Difficulty d")
public class Difficulty implements Serializable{
    
    @Id
    private String id;
    
    @NotNull
    private String level;

    private int zoom;

    @ManyToMany
    private Set<Distance> distance = new TreeSet<Distance>();

    @ManyToMany
    private Set<Multiplier> multiplier = new TreeSet<Multiplier>();

    public Difficulty() {
    }
    
    public Difficulty(String level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public Set<Distance> getDistance() {
        return distance;
    }

    public void setDistance(Set<Distance> distance) {
        this.distance = distance;
    }

    public Set<Multiplier> getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Set<Multiplier> multiplier) {
        this.multiplier = multiplier;
    }
}
