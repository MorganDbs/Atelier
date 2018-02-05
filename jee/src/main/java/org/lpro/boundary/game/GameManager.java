/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.difficulty;

import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.CacheStoreMode;
import org.lpro.entity.Difficulty;
import org.lpro.entity.Game;
import org.lpro.entity.Serie;
import token.Token;

/**
 *
 * @author morgan
 */
public class GameManager {
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    DifficultyManager dm;
    
    public List<Game> findAll() {
        Query q = this.em.createNamedQuery("Game.findAll", Game.class);
        q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return q.getResultList();
    }
    
    public void addGames(Serie s){
        List<Difficulty> d = this.dm.findAll();
        
        d.forEach(df ->{
           this.save(new Game(s.getId(), df.getId())); 
        });
    }
    
    public void save(Game g){
        g.setId(UUID.randomUUID().toString());
        g.setToken(new Token().generateRandomString());
        this.em.merge(g);
    }
}
