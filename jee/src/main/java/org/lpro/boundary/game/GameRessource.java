/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.score;

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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.boundary.difficulty.DifficultyManager;
import org.lpro.boundary.game.GameManager;
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
    public Response addScore(JsonObject jsonScore, @Context UriInfo uriInfo) throws java.text.ParseException {
        System.out.println("qsbdhklqsmbdjlkqsljqdskldsqlmk");
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
    
    private JsonObject buildGameJson(Serie s, Game g, List<Picture> pictures){

        JsonArrayBuilder picturesJA = Json.createArrayBuilder();

        pictures.forEach((picture ->{
            JsonObject coords = Json.createObjectBuilder()
                    .add("lat", picture.getLat())
                    .add("lng", picture.getLng())
                    .build();

            JsonObject pic = Json.createObjectBuilder()
                    .add("lat", picture.getUrl())
                    .add("coords", coords)
                    .build();

            picturesJA.add(pic);
        }));

        JsonObject coords = Json.createObjectBuilder()
                .add("lat", s.getLat())
                .add("lng", s.getLng())
                .build();

        JsonObject game = Json.createObjectBuilder()
                .add("id", g.getId())
                .add("nickname", g.getNickname())
                .add("token", g.getToken())
                .build();


        JsonObject serie = Json.createObjectBuilder()
                .add("id", s.getId())
                .add("name", s.getName())
                .add("city", s.getCity())
                .add("description", s.getDescription())
                .add("coords", coords)
                .add("game", game)
                .add("pictures", picturesJA)
                .build();

        return Json.createObjectBuilder()
                .add("type", "ressource")
                .add("serie", serie)
                .build();
    }
}
