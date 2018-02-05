/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.serie;

import java.util.Set;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.lpro.entity.Picture;
import org.lpro.entity.Serie;

/**
 *
 * @author morgan
 */
public class SerieManager {
    
    @PersistenceContext
    EntityManager em;
    
    public Serie saveWithPictures(Serie s, Set<Picture> p){
        s.setId(UUID.randomUUID().toString());
        s.setPicture(p);
        return this.em.merge(s);
    }
}
