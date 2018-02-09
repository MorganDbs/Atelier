<template>
  <div class="hello">
    <div classe="header">
      <div class="jumbotron">

        <div classe="img">
          <img style="background-color:#ADD8E6; border-radius: 2% 2%;" src="../assets/logoMonde.png" alt="GeoQuizz">
        </div>
        <div class="descGeoquizz">
          <h1 class="display-4">Geo Quizz</h1>
          <p class="lead">Le seul jeu qui te fera passer, de bons moments. Enfin, un moyen de mélanger deux de tes passions, la géographie et le jeu.</p>
          <p class="lead">C'est parti !</p>
        </div>
      </div>
      <hr/>

    </div>
    <div class="bloc">
    <h1>Geo Quizz Sign up</h1>
    <form @submit.prevent="signup()">
      <label for="fname">Nom et prénom</label>
      <input v-validate="'required|email'" type="text" v-model="members.fullname" id="fname" name="firstname" placeholder="Votre nom et prénom.." required>

      <label for="email">Email</label>
      <input v-validate="'required|email'" type="email" v-model="members.mail" id="email" name="email" placeholder="Votre email .." required>

      <label for="password">Mot de passe</label>
      <input type="password" v-model="members.password" id="password" name="password" placeholder="Votre mot de passe.." required>

      <label for="password">Confirmation mot de passe</label>
      <input type="password" v-model="confirmPassword" id="password2" name="password2" placeholder="Confirmer votre mot de passe.." required>

      <input type="submit" value="Submit">
    </form>

  </div>
    <hr/>
    <div  class="text-center">
      <p>Vous avez déjà un compte ? Vous pouvez vous connecter, en cliquant sur le lien suivant !</p>
      <router-link class="btnCo" to="signin">Connectez-vous</router-link>
    </div>
</div>

</template>

<script>
  import confApi from '../configApi'
  import router from '../router'

  export default {
    name: 'signup',
    data () {
      return {
        members:{fullname:'',
          mail: '',
          password:''
        },
        confirmPassword: ''

      }
    },

    methods: {
      signup() {
        if (this.members.password === this.confirmPassword) {
          confApi.post('/signup', this.members).then((response) => {
            alert('Vous etes inscrit ! Veuillez vous connecter.')
            router.push("signin")
          }).catch((error) => {

              alert("Nous n'avons pas réussi à vous inscrire, désolé !");

          })

        } else {
          alert('Veuillez verifier votre mot de passe. Ils ne sont pas identiques.')
        }

      }
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
  /*
  Champs du formulaire
   */
  input[type=text], input[type=email], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }
  input[type=password], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }

  input[type=submit] {
    width:50%;
    background-color: #ADD8E6;
    color: white;
    padding: 14px 20px;
    margin: 1% 24%;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  input[type=submit]:hover {
    background-color:  #ADD8E8;
  }
  /*
  div correspondant au formulaie
   */

  div {
    border-radius: 5px;
    background-color: white;
    padding: 20px;
  }
  .bloc{
    background-color: #f2f2f2;

  }
  /*
  En-tête du site
   */
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
</style>
