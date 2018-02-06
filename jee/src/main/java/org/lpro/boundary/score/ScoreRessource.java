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
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
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
import org.lpro.entity.Score;
import org.lpro.entity.Serie;

@Stateless
@Path("scores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Score")
public class ScoreRessource {
    
    @Inject
    SerieManager sm;
    
    @Inject
    DifficultyManager dm;
    
    @Inject
    ScoreManager scorm;
    
    @POST
    @ApiOperation(value = "Crée un score", notes = "Crée un score à partir du JSON fourni")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 417, message = "EXPECTATION_FAILED"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response addScore(JsonObject scoreBody, @QueryParam("token") String token, @HeaderParam("X-geoguizz-token") String header, @Context UriInfo uriInfo) throws java.text.ParseException {
        Difficulty d;
        
        JsonObjectBuilder errors = Json.createObjectBuilder();
        JsonObject jsonScore = null;
        String errorsList = "";
        Boolean flag = false;
        boolean flag_token = false;
        
        if(!scoreBody.containsKey("score") || scoreBody.isNull("score")){
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }else{
            jsonScore = scoreBody.getJsonObject("score");
        }
        
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
        
        if (token == null && header == null) {
            return Response.status(Response.Status.FORBIDDEN).entity(
                    Json.createObjectBuilder()
                            .add("error", "Il faut renseigner un token")
                            .build()
            ).build();
        }
        
        String tokenGame = (token != null) ? token : header;
        
        flag_token = s.getGame().stream().anyMatch(t -> {return tokenGame.equals(t.getToken()); });
        
        if(!flag_token){
            return Response.status(Response.Status.NOT_FOUND).entity(
                    Json.createObjectBuilder()
                            .add("error", "Le token pour cette série n'existe pas")
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
        
        try{
          d = this.dm.findById(jsonScore.getString("id_difficulty"));
        }catch(NullPointerException e){
            return Response.status(Response.Status.NOT_FOUND).entity(
                    Json.createObjectBuilder()
                            .add("error", "La difficulte n'existe pas")
                            .build()
            ).build();
        }      
        
        if(!jsonScore.containsKey("score") || jsonScore.isNull("score")){
            errorsList += "Il faut renseigner un score. ";
            flag = true;
        }
        
        if(flag){                        
            errors.add("errors", errorsList);
            JsonObject json_errors = errors.build();
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(json_errors).build();
        }
        
        Score score = new Score(jsonScore.getString("id_serie"), jsonScore.getString("id_difficulty"), jsonScore.getString("nickname"), jsonScore.getInt("score"));
        score = this.scorm.saveNewScore(score);
        
        JsonObject succes = Json.createObjectBuilder()
                .add("succes", "Le score a été sauvegardé")
                .build();
        
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+score.getId()).build();
        return Response.created(uri).entity(succes).build();
       
    }
}
