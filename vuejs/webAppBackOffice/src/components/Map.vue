<template>
  <div>
    <div class="hello">
      <div classe="header">
        <div class="jumbotron">

          <div classe="img">
            <img style="background-color:#ADD8E6; border-radius: 2% 2%;" src="../assets/logoMonde.png" alt="geoQuizz">
          </div>
          <div class="descGeoquizz">
            <h1 class="display-4">Geo Quizz</h1>
            <p class="lead">Le seul jeu qui te feras passer de bon moment. Enfin, un moyen de mélanger deux de tes passions, la geographie et le jeu.</p>
            <p class="lead">C'est partie !</p>
          </div>
        </div><hr/>

      </div>


      <div class="profLogout">
        <button v-on:click="logOut()">Se deconnecter</button>
      </div>
    </div>

    <div id="app">


      <div class="divMap">
        <h2>Choisissez les différents points de votre serie</h2>

        <v-map id="map" :zoom=13 :center="[cityCoord.lat, cityCoord.lng]">
          <v-tilelayer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"></v-tilelayer>
          <v-marker :lat-lng="[47.413220, -1.219482]"></v-marker>
        </v-map>
      </div>
    </div>



  </div>
</template>

<script>
  import axios from 'axios'
  import router from '../router'
  export default {

    name: 'map',
    props: ['cityCoord'],
    data (){
      return {

      }

    },
    methods: {

      logOut(){
        sessionStorage.clear()
        alert("You're disconnect");
        router.push({name: 'home'})


      },
      createSerie(){

      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #map{
    width: 55vw;
    height: 70vh;
  }
  h1, h2 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }

  .profLogout{

    display: flex;
    flex-direction: row;
    width: 100%;
    align-items: center;
    justify-content: flex-end;
  }

  button {
    margin-left: 2px;
    background-color: #ADD8E6; /* blue */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
  }
  .hello{
    display: flex;
    flex-direction: row;
    width: 100%;
    height: 50%;
    align-items: center;
    justify-content: center;
    padding-bottom: 20px;
  }

  div {
    border-radius: 5px;
    background-color: white;
    padding: 20px;
  }

  .home{
    display: flex;
    background-color: 	#b3ffb3;

  }

  .channels{
    width : 100%;
  }

  .members{
    width :200px;
    background-color:	#b3ffb3;
  }
  .img{
    background-color: cornflowerblue;
    align-items: flex-start;
  }
  .jumbotron{
    display: flex;
    flex-direction: row;
  }
  .descGeoquizz{
    align-items: flex-start;
    display: flex;
    flex-direction: column;
  }

  #app{
    display: flex;
    flex-direction: row;
  }




</style>
