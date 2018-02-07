/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.game;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.List;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.boundary.difficulty.DifficultyManager;
import org.lpro.boundary.serie.SerieManager;
import org.lpro.entity.Difficulty;
import org.lpro.entity.Game;
import org.lpro.entity.Picture;
import org.lpro.entity.Serie;

@Stateless
@Path("games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Game")
public class GameRessource {
    
    @Inject
    SerieManager sm;
    
    @Inject
    DifficultyManager dm;
    
    @Inject
    GameManager gm;
    
    @POST
    @ApiOperation(value = "Crée une game", notes = "Crée une game à partir du JSON fourni")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 417, message = "EXPECTATION_FAILED"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response createGame(JsonObject jsonScore, @Context UriInfo uriInfo) throws java.text.ParseException {
        JsonObjectBuilder errors = Json.createObjectBuilder();
        String errorsList = "";
        boolean flag = false;
        
        
        if(!jsonScore.containsKey("id_serie") || jsonScore.isNull("id_serie") || jsonScore.getString("id_serie").isEmpty()){
            errorsList += "Il faut renseigner un id de série. ";
            flag = true;
        }else if(!Pattern.matches("^[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}$", jsonScore.getString("id_serie"))){
            errorsList += "Il faut respecter la casse de l'id de série. ";
            flag = true;
        }
        
        Serie s = this.sm.findById(jsonScore.getString("id_serie"));
        
        if(s == null){
            return Response.status(Response.Status.NOT_FOUND).entity(
                    Json.createObjectBuilder()
                            .add("error", "La série n'existe pas")
                            .build()
            ).build();
        }
        

        if(!jsonScore.containsKey("nickname") || jsonScore.isNull("nickname") || jsonScore.getString("nickname").isEmpty()){
            errorsList += "Il faut renseigner un nickname. ";
            flag = true;
        }else if(!Pattern.matches("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", jsonScore.getString("nickname"))){
            errorsList += "Il faut respecter la casse du nickname. ";
            flag = true;
        }
        
        if(!jsonScore.containsKey("id_difficulty") || jsonScore.isNull("id_difficulty") || jsonScore.getString("id_difficulty").isEmpty()){
            errorsList += "Il faut renseigner un id de difficulte. ";
            flag = true;
        }else if(!Pattern.matches("^\\d$", jsonScore.getString("id_difficulty"))){
            errorsList += "Il faut respecter la casse de l'id de difficulty. ";
            flag = true;
        }
        
        
        Difficulty d = this.dm.findById(jsonScore.getString("id_difficulty"));
        
        if(d == null){
            return Response.status(Response.Status.NOT_FOUND).entity(
                    Json.createObjectBuilder()
                            .add("error", "La difficulte n'existe pas")
                            .build()
            ).build();
        }  
        
        if(flag){                        
            errors.add("errors", errorsList);
            JsonObject json_errors = errors.build();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(json_errors).build();
        }
        
        Game game = new Game(jsonScore.getString("id_serie"), jsonScore.getString("id_difficulty"), jsonScore.getString("nickname"));
        game = this.gm.create(game);
        List<Picture> pictures = this.sm.pickRandomPictures(s, 10);
        
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+game.getId()).build();
        return Response.created(uri).entity(this.buildGameJson(s, game, pictures)).build();
       
    }
    
    @PUT
    @ApiOperation(value = "Crée une game", notes = "Crée une game à partir du JSON fourni")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 417, message = "EXPECTATION_FAILED"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response scoreGame(JsonObject jsonScore, @QueryParam("token") String token, @HeaderParam("X-geoquizz-token") String header, @Context UriInfo uriInfo) throws java.text.ParseException {
        
        if (token == null && header == null) {
            return Response.status(Response.Status.FORBIDDEN).entity(
                    Json.createObjectBuilder()
                            .add("error", "Il faut renseigner un token")
                            .build()
            ).build();
        }

        String tokenGame = (token != null) ? token : header;

        Game g = this.gm.findByToken(tokenGame);

        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity(
                    Json.createObjectBuilder()
                            .add("error", "Pas de game pour ce token")
                            .build()
            ).build();
        }
        
        if(!jsonScore.containsKey("score") || jsonScore.isNull("score") || jsonScore.getString("score").isEmpty()){
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(
                    Json.createObjectBuilder()
                            .add("error", "Il faut rentrer un score")
                            .build()
            ).build();
        }else if(!Pattern.matches("^\\d+$", jsonScore.getString("score"))){
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(
                    Json.createObjectBuilder()
                            .add("error", "Il faut respecter la casse du score")
                            .build()
            ).build();
        }
        
        g = this.gm.updateScore(g, Integer.parseInt(jsonScore.getString("score")));
        
        JsonObject succes = Json.createObjectBuilder()
                .add("success", "Score enregistré")
                .build();
        
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+g.getId()).build();
        return Response.created(uri).entity(succes).build();
       
    }
    
    private JsonObject buildGameJson(Serie s, Game g, List<Picture> pictures){

        JsonArrayBuilder picturesJA = Json.createArrayBuilder();

        pictures.forEach((picture ->{
            JsonObject coords = Json.createObjectBuilder()
                    .add("lat", picture.getLat())
                    .add("lng", picture.getLng())
                    .build();

            JsonObject pic = Json.createObjectBuilder()
                    .add("picture", picture.getUrl())
                    .add("coords", coords)
                    .build();

            picturesJA.add(pic);
        }));

        JsonObject coords = Json.createObjectBuilder()
                .add("lat", s.getLat())
                .add("lng", s.getLng())
                .build();

        JsonObject serie = Json.createObjectBuilder()
                .add("id", s.getId())
                .add("name", s.getName())
                .add("city", s.getCity())
                .add("description", s.getDescription())
                .add("coords", coords)
                .add("pictures", picturesJA)
                .build();

        return Json.createObjectBuilder()
                .add("type", "ressource")
                .add("token", g.getToken())
                .add("serie", serie)
                .build();
    }
}
