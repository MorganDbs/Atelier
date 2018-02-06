/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.score;

import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.lpro.entity.Score;

/**
 *
 * @author morgan
 */
public class ScoreManager {
    
    @PersistenceContext
    EntityManager em;
    
 
    public Score saveNewScore(Score s){
        s.setId(UUID.randomUUID().toString());
        return this.em.merge(s);
    }
    
}
