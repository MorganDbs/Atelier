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

        classes.add(com.github.phillipkruger.apiee.ApieeService.class);
        classes.add(org.lpro.boundary.account.signup.SignupBoundary.class);
        classes.add(org.lpro.boundary.account.signin.SigninBoundary.class);
        classes.add(org.lpro.boundary.account.signout.SignoutBoundary.class);
        classes.add(org.lpro.boundary.serie.SerieRessource.class);

        return classes;
    }
}
