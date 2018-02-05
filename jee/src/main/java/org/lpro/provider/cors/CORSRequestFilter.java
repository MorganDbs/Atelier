package org.lpro.provider.cors;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class CORSRequestFilter implements ContainerRequestFilter {

    private final static Logger logger = Logger.getLogger(CORSRequestFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestCtx) throws IOException {
        logger.log(Level.INFO, "Execution du filtre Request: {0}", requestCtx.getRequest().getMethod());
        if (requestCtx.getRequest().getMethod().equals("OPTIONS")) {
            logger.info("Detection HTTP Method (OPTIONS)");
            requestCtx.abortWith(Response.status(Response.Status.OK).build());
        }
    }
}