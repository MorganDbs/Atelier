package org.lpro.boundary.account.signin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.lpro.boundary.account.AccountManager;
import org.lpro.control.KeyManagement;
import org.lpro.control.PasswordManagement;
import org.lpro.entity.Account;
import org.mindrot.jbcrypt.BCrypt;

@Path("/signin")
@Api(value = "Connexion")
public class SigninBoundary {

    @Inject
    private KeyManagement keyManagement;

    @Inject
    private AccountManager am;

    @Context
    private UriInfo uriInfo;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @ApiOperation(value = "Authentifie un utilisateur", notes = "Authentifie un utilisateur à partir du JSON fourni")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response signinUser(Account utilisateur) {
        try {
            String mail = utilisateur.getMail();
            String password = utilisateur.getPassword();

            String digest = PasswordManagement.digestPassword(password);
            System.out.println("hash " + digest);
            Account one = this.am.findUser(mail);

            if (one != null) {
                this.signin(mail, password, one);

                String token = this.issueToken(mail);

                return Response.ok(Json.createObjectBuilder()
                        .add("fullname", one.getFullname())
                        .add("mail", one.getMail())
                        .add("token", token)
                        .add("success", "Connexion réussie")
                        .build()
                ).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity(
                        Json.createObjectBuilder()
                                .add("error", "Ce compte n'existe pas")
                                .build()
                ).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(
                    Json.createObjectBuilder()
                            .add("error", "L'adresse mail ou le mot de passe est incorrect")
                            .build()
            ).build();
        }
    }

    private void signin(String mail, String password, Account user) throws Exception {
        if (user != null) {
            if (mail.equals(user.getMail()) && BCrypt.checkpw(password, user.getPassword())) {

            } else {
                throw new NotAuthorizedException("Problème d'signin");
            }
        } else {
            throw new NotAuthorizedException("Compte introuvable");
        }
    }

    private String issueToken(String mail) {
        Key key = keyManagement.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(mail)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                // .setExpiration(toDate(LocalDateTime.now().plusMinutes(5L)))
                .setExpiration(this.toDate(LocalDateTime.now().plusHours(2L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        System.out.println(">>>> token/key : " + jwtToken + " -- " + key);
        return jwtToken;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
