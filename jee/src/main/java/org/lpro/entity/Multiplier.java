package org.lpro.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name="Multiplier.findAll",query="SELECT m FROM Multiplier m")
public class Multiplier implements Serializable{

    @Id
    private String id;

    private int multiplier;

    private int time;

    @ManyToMany
    private Set<Difficulty> difficulty = new HashSet<Difficulty>();

    public Multiplier() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Set<Difficulty> getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Set<Difficulty> difficulty) {
        this.difficulty = difficulty;
    }
}
