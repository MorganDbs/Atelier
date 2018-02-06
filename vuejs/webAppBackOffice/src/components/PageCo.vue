<template>
  <div>
  <div class="hello">
    <div classe="header">
      <div class="jumbotron">
          <div classe="img">
            <img style="background-color:#ADD8E6; border-radius: 2% 2%;" src="../assets/logoMonde.png" alt="CO-OP">
          </div>
          <h1 class="display-4">Fluid jumbotron</h1>
          <p class="lead">This is a modified jumbotron that occupies the entire horizontal space of its parent.</p>

      </div>

    </div>

    <div id="app" style="height: 100%">
      <v-map :zoom=13 :center="[47.413220, -1.219482]">
        <v-tilelayer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"></v-tilelayer>
        <v-marker :lat-lng="[47.413220, -1.219482]"></v-marker>
      </v-map>
    </div>

    <div class="profLogout">
      <button v-on:click="logOut()">Se deconnecter</button>
    </div>
  </div>




  </div>
</template>

<script>
  //import confApi from '../configApi'
  import router from '../router'
  export default {

    name: 'PageCo',
    data (){
      return {

      }
    },
    created() {
      /**confApi.get('/members').then((response) =>{
        this.members= response.data;
      }).catch((error)=> {
        console.log(error);
      })

      confApi.get('/channels').then((response) =>{
        this.channels= response.data;
      }).catch((error)=> {
        console.log(error);
      })
*/

    },
    methods: {
      /*
      logOut(){
        confApi.delete('members/signout')
          .then((response)=> {
          console.log(response);
          alert("You're disconnect");
            router.push({name: 'Home'})
            sessionStorage.clear()
        })

      },
      */
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
  a {
    color: #42b983;
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
    background-color: #4CAF50; /* Green */
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




</style>
