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
@NamedQuery(name="Picture.findAll",query="SELECT p FROM Picture p")
public class Picture implements Serializable {

    @Id
    private String id;

    @NotNull
    private String urlPicture;

    @NotNull
    private double lat, lng;

    @ManyToMany(mappedBy = "picture")
    private Set<Serie> serie = new HashSet<Serie>();

    public Picture() { }

    public Picture(String url, double lat, double lng) {
        this.urlPicture = url;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return urlPicture;
    }

    public void setUrl(String url) {
        this.urlPicture = url;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Set<Serie> getSerie() {
        return serie;
    }

    public void setSerie(Set<Serie> serie) {
        this.serie = serie;
    }
}