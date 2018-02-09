/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.serie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.lpro.boundary.game.GameManager;
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
    
    public Serie findById(String id){
        return this.em.find(Serie.class, id);
    }
    
    public Serie saveNewSeries(Serie s, Set<Picture> p){
        s.setId(UUID.randomUUID().toString());
        s.setPicture(p);
        return this.em.merge(s);
    }
    
    public Serie saveNewPicturesSeries(Serie s, Set<Picture> p){
        s.getPicture().addAll(p);
        return this.em.merge(s);
    }
    
    public List<Picture> pickRandomPictures(Serie s, int nbr){
        List<Integer> indexes = new ArrayList<>();
        List<Picture> res = new ArrayList<>();
        for(int i = 0; i < s.getPicture().size(); i++){
            indexes.add(i);
        }
        Collections.shuffle(indexes);
        List<Integer> sub = indexes.subList(0, nbr);
        
        for(int i : sub){
            res.add(new ArrayList<Picture>(s.getPicture()).get(i));
        }
        
        return res;
    }
}
