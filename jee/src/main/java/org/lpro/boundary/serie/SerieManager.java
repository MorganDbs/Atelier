/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.serie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.lpro.boundary.difficulty.GameManager;
import org.lpro.entity.Picture;
import org.lpro.entity.Serie;
import javax.persistence.Query;
import javax.persistence.CacheStoreMode;
import org.lpro.entity.Game;

/**
 *
 * @author morgan
 */
public class SerieManager {
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    GameManager gm;
    
    public List<Serie> findAll(){
        Query q = this.em.createNamedQuery("Serie.findAll", Serie.class);
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }
    
    public Serie saveNewSeries(Serie s, Set<Picture> p){
        s.setId(UUID.randomUUID().toString());
        s.setPicture(p);
        this.gm.addGames(s);
        s.setGame(new HashSet<Game>(this.gm.findBySerieId(s)));
        return this.em.merge(s);
    }
}
