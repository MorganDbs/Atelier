package org.lpro.boundary.authentification;

import org.lpro.entity.Utilisateur;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;

@Stateless
@Transactional
public class UtilisateurManager {

    @PersistenceContext
    EntityManager em;

    public Utilisateur findById(String id) {
        return this.em.find(Utilisateur.class, id);
    }

    public Utilisateur findUtilisateur(String mail) {
        Utilisateur u = null;

        TypedQuery<Utilisateur> query = this.em.createQuery("SELECT u FROM Utilisateur u WHERE u.mail = :mail", Utilisateur.class);
        query.setParameter("mail", mail);

        try {
            u = query.getSingleResult();
        } catch (NoResultException e) {
            u = null;
        }
        return u;
    }

}
