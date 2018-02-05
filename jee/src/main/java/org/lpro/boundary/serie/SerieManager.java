/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.serie;

import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.lpro.boundary.difficulty.GameManager;
import org.lpro.entity.Picture;
import org.lpro.entity.Serie;

/**
 *
 * @author morgan
 */
public class SerieManager {
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    GameManager gm;
    
    public Serie saveNewSeries(Serie s, Set<Picture> p){
        s.setId(UUID.randomUUID().toString());
        s.setPicture(p);
        this.gm.addGames(s);
        return this.em.merge(s);
    }
}
