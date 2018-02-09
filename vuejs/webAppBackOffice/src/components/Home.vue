<template>
  <div class="hello">

    <div classe="header">
      <div class="jumbotron">

        <div classe="img">
          <img style="background-color:#ADD8E6; border-radius: 2% 2%;" src="../assets/logoMonde.png" alt="CO-OP">
        </div>
        <div class="descGeoquizz">
          <h1 class="display-4">Geo Quizz</h1>
          <p class="lead">Le seul jeu qui te fera passer, de bons moments. Enfin, un moyen de mélanger deux de tes passions, la géographie et le jeu.</p>
          <p class="lead">C'est parti !</p>
        </div>
      </div>
      <hr/>

    </div>
    <h1 class="text-center">Welcome</h1>
    <div class="formulaire">
      <router-link class="btnCo text-center" to="signin">Sign In</router-link>
      <router-link class="btnCo text-center" to="signup">Sign Up</router-link>
    </div>
  </div>
</template>

<script>
  // import configApi from '../configApi'
  import router from '../router'

  export default {
    name: 'home',
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
  /*
  En-tête du site, div, bouton et description
  */
  .btnCo {
    width:50vw;
    background-color: #ADD8E6;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  .btnCo:hover {
    background-color: #ADD8E8;
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
  /*
  Formulaire
   */
  .formulaire{
    width: 100%;
    margin-top:2%;
    display:flex;
    flex-direction: column;
    align-items: center;

    background-color: #f2f2f2;
  }
  div {
    border-radius: 5px;
    background-color:white;
    padding: 20px;
  }
</style>
