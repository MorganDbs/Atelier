package org.lpro.boundary.account.signout;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.lpro.boundary.account.AccountManager;
import org.lpro.entity.Account;
import org.lpro.provider.Secured;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/signout")
@Api(value = "Déconnexion")
public class SignoutBoundary {

    @Inject
    private AccountManager am;

    @Context
    private UriInfo uriInfo;

    @DELETE
    @Secured
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "Déconnecte un utilisateur", notes = "Déconnecte un utilisateur à partir du JSON fourni")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response signoutUser(
            @DefaultValue("") @QueryParam("tokenCo") String tokenParam,
            @DefaultValue("") @HeaderParam("Authorization") String tokenHeader
    ) {
        if (tokenParam.isEmpty() && tokenHeader.isEmpty()) {
            return Response.status(Response.Status.FORBIDDEN).entity(
                    Json.createObjectBuilder()
                            .add("error", "Le token n'existe pas")
                            .build()
            ).build();
        } else {
            String token = (tokenParam.isEmpty()) ? tokenHeader.split(" ")[1] : tokenParam;

            Account user = this.am.findUserByToken(token);

            if (user != null) {
                user = this.am.signout(user);

                if (user != null) {
                    return Response.ok(Json.createObjectBuilder()
                            .add("success", "Déconnexion réussie")
                            .build()
                    ).build();
                } else {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
                            Json.createObjectBuilder()
                                    .add("error", "Une erreur est survenue lors de la déconnexion")
                                    .build()
                    ).build();
                }
            } else {
                return Response.status(Response.Status.FORBIDDEN).entity(
                        Json.createObjectBuilder()
                                .add("error", "Le token n'est pas le bon")
                                .build()
                ).build();
            }
        }
    }
}
