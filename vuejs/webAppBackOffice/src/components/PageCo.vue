<template>
  <div>
  <div class="hello">
    <div classe="header">
      <div class="jumbotron">

        <div classe="img">
          <img style="background-color:#ADD8E6; border-radius: 2% 2%;" src="../assets/logoMonde.png" alt="CO-OP">
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

      <div class="formulaire">
        <h1>Créer une serie</h1>
      <form @submit.prevent="createSerie()">
        <label for="name">Nom de la série</label>
        <input type="text" v-model="serie.name" id="name" name="name" placeholder="Le nom de la serie " required>

        <label for="description">Description</label>
        <input type="text" v-model="serie.description" id="description" name="description" placeholder="Votre description" required>

        <label for="city">Ville</label>
        <input type="text" v-model="serie.city" id="city" name="city" placeholder="Votre ville" required>


        <input type="submit" value="Submit">
      </form>
      </div>
    </div>



  </div>
</template>

<script>
  import axios from 'axios'
  import router from '../router'
  export default {

    name: 'pageCo',


    data (){
      return {
        serie: {
          name: '',
          description: '',
          city: '',
          coords: {
            lat: '',
            lng: ''
          },
          pictures: []
        },
        cityCoord:{
          lat:'',
          lng:''
        }
      }

    },
    methods: {

      logOut(){
        sessionStorage.clear()
        alert("You're disconnect");
        router.push({name: 'home'})


      },
      createSerie(){
        axios.get(`https://maps.googleapis.com/maps/api/geocode/json?address=`+this.serie.city+`,+FR&key=AIzaSyCdIprtWN6lsubVYIiWCkQUGNEoLj_AxDo`)
          .then(response => {
            // JSON responses are automatically parsed.
            console.log(response.data.results[0].geometry.location)
            this.cityCoord.lat=response.data.results[0].geometry.location.lat;
            this.cityCoord.lng=response.data.results[0].geometry.location.lng;
            router.push({name:'map'})
          })
          .catch(e => {
            alert(e)
          })

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

  input[type=submit] {
    width:50%;
    background-color: #ADD8E6;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  input[type=submit]:hover {
    background-color:  #ADD8E8;
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
  .formulaire{
    width: 40vw;
    margin-top:2%;
  }




</style>
