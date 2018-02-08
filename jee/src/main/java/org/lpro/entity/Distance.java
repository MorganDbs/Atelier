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
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name="Distance.findAll",query="SELECT d FROM Distance d")
public class Distance implements Serializable, Comparable<Distance> {

    @Id
    private String id;

    private int distance;

    private int points;

    @ManyToMany(mappedBy = "distance")
    private Set<Difficulty> difficulty = new HashSet<Difficulty>();

    public Distance() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Set<Difficulty> getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Set<Difficulty> difficulty) {
        this.difficulty = difficulty;
    }

    public int compareTo(Distance distance)
    {
        return Integer.compare(this.getDistance(), distance.getDistance());
    }
}
