/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.game;

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
import javax.persistence.TypedQuery;
import org.lpro.boundary.difficulty.DifficultyManager;
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
    
    public Game findById(String id){
        return this.em.find(Game.class, id);
    }
    
    public List<Game> findBySerieId(Serie s) {
        String query = "SELECT g FROM Game g WHERE g.id_serie = '" + s.getId() + "'";
        Query q = this.em.createQuery(query);
        return q.getResultList();
    }
    
    public Game findBySerieIdAndToken(Serie s, String token) {
        TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g WHERE g.id_serie = '" + s.getId() + "' and token = '"+token+"'", Game.class);
        return query.getSingleResult();
    }
    
    public Game create(Game g){
        g.setId(UUID.randomUUID().toString());
        g.setToken(new Token().generateRandomString());
        return this.em.merge(g);
    }
}
