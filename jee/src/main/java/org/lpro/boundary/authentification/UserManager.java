package org.lpro.boundary.authentification;

import org.lpro.entity.User;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;

@Stateless
@Transactional
public class UserManager {

    @PersistenceContext
    EntityManager em;

    public User findById(String id) {
        return this.em.find(User.class, id);
    }

    public User findUser(String mail) {
        User u = null;

        TypedQuery<User> query = this.em.createQuery("SELECT u FROM User u WHERE u.mail = :mail", User.class);
        query.setParameter("mail", mail);

        try {
            u = query.getSingleResult();
        } catch (NoResultException e) {
            u = null;
        }
        return u;
    }

}
