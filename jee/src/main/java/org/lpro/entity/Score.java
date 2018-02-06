/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name="Score.findAll",query="SELECT s FROM Score s")
public class Score implements Serializable{
    
    @Id
    private String id;
    
    @NotNull
    private String id_serie, id_difficulty, nickname;
    @NotNull
    private int score;
    
    public Score(){
        
    }
    
    public Score(String id_serie, String id_difficulty, String nickname, int score) {
        this.id_serie = id_serie;
        this.id_difficulty = id_difficulty;
        this.nickname = nickname;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_serie() {
        return id_serie;
    }

    public void setId_serie(String id_serie) {
        this.id_serie = id_serie;
    }

    public String getId_difficulty() {
        return id_difficulty;
    }

    public void setId_difficulty(String id_difficulty) {
        this.id_difficulty = id_difficulty;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
}
