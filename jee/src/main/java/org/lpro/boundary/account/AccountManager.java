package org.lpro.boundary.account;

import org.lpro.control.PasswordManagement;
import org.lpro.entity.Account;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.awt.font.TextAttribute;
import java.util.UUID;

@Stateless
@Transactional
public class AccountManager {

    @PersistenceContext
    EntityManager em;

    public Account findById(String id) {
        return this.em.find(Account.class, id);
    }

    public Account findUser(String mail) {
        Account u = null;

        TypedQuery<Account> query = this.em.createQuery("SELECT u FROM Account u WHERE u.mail = :mail", Account.class);
        query.setParameter("mail", mail);

        try {
            u = query.getSingleResult();
        } catch (NoResultException e) {
            u = null;
        }
        return u;
    }

    public Account findUserByToken(String token) {
        Account u = null;

        TypedQuery<Account> query = this.em.createQuery("SELECT u FROM Account u WHERE u.token = :token", Account.class);
        query.setParameter("token", token);

        try {
            u = query.getSingleResult();
        } catch (NoResultException e) {
            u = null;
        }
        return u;
    }

    public Account signup(String fullname, String mail, String password) {
        Account newUser = new Account(fullname, mail, PasswordManagement.digestPassword(password));
        newUser.setId(UUID.randomUUID().toString());

        return this.em.merge(newUser);
    }
}
