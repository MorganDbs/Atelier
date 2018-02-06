/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.difficulty;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.lpro.entity.Difficulty;
import javax.persistence.Query;
import javax.persistence.CacheStoreMode;
import org.lpro.entity.Game;

/**
 *
 * @author morgan
 */
public class DifficultyManager {
    @PersistenceContext
    EntityManager em;
    
    public Difficulty findById(String id){
        return this.em.find(Difficulty.class, id);
    }
    
    public List<Difficulty> findAll() {
        Query q = this.em.createNamedQuery("Difficulty.findAll", Difficulty.class);
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }
}
