package org.lpro.boundary.account.signup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.lpro.boundary.account.AccountManager;
import org.lpro.entity.Account;
import org.lpro.entity.apiModels.CreateAccount;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/signup")
@Api(value = "Inscription")
public class SignupBoundary {

    @Inject
    private AccountManager am;

    @Context
    private UriInfo uriInfo;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "Crée un compte pour un utilisateur", notes = "Crée un compte pour un utilisateur à partir du JSON fourni")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response signupUser(CreateAccount newAccount) {
        if (newAccount != null) {
            String fullname = newAccount.getFullname();
            String mail = newAccount.getMail();
            String password = newAccount.getPassword();

            if (fullname != null && mail != null && password != null) {
                if (fullname.matches("([a-zA-ZáàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ\\s-]+)")) {
                    if (mail.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                        Account user = this.am.findUser(mail);

                        if (user == null) {
                            user = this.am.signup(fullname, mail, password);

                            if (user != null) {
                                return Response.ok(Json.createObjectBuilder()
                                        .add("id_user", user.getId())
                                        .add("fullname", user.getFullname())
                                        .add("mail", user.getMail())
                                        .add("success", "Le compte a bien été créé")
                                        .build()
                                ).build();
                            } else {
                                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
                                        Json.createObjectBuilder()
                                                .add("error", "Une erreur est survenue lors de l'inscription")
                                                .build()
                                ).build();
                            }
                        } else {
                            return Response.status(Response.Status.FORBIDDEN).entity(
                                    Json.createObjectBuilder()
                                            .add("error", "L'adresse mail est déjà utilisée")
                                            .build()
                            ).build();
                        }
                    } else {
                        return Response.status(Response.Status.BAD_REQUEST).entity(
                                Json.createObjectBuilder()
                                        .add("error", "L'adresse mail est incorrecte")
                                        .build()
                        ).build();
                    }
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity(
                            Json.createObjectBuilder()
                                    .add("error", "Le nom et le prénom sont incorrectes")
                                    .build()
                    ).build();
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(
                        Json.createObjectBuilder()
                                .add("error", "Les informations d'inscription sont incomplètes")
                                .build()
                ).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    Json.createObjectBuilder()
                            .add("error", "Il n'y a aucune informations d'inscription")
                            .build()
            ).build();
        }
    }
}
