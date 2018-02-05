package com.airhacks;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        /*classes.add(com.github.phillipkruger.apiee.ApieeService.class);
        classes.add(org.lpro.boundary.categorie.CategorieRessource.class);
        classes.add(org.lpro.boundary.categorie.exception.CategorieNotFound.class);
        classes.add(org.lpro.boundary.categorie.mapper.CategorieNotFoundMapper.class);
        classes.add(org.lpro.boundary.sandwich.SandwichRessource.class);
        classes.add(org.lpro.boundary.sandwich.exception.SandwichNotFound.class);
        classes.add(org.lpro.boundary.sandwich.mapper.SandwichNotFoundMapper.class);
        classes.add(org.lpro.boundary.authentification.UtilisateurBoundary.class);
        classes.add(org.lpro.boundary.carte.CarteRessource.class);
        classes.add(org.lpro.boundary.commande.CommandeRessource.class);*/

        return classes;
    }
}
