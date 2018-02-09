<template>
  <div class="jumbotron vertical-center bg">
    <div class="container">
      <div class="row">
        <div class="col-md-2 offset-5">
          <h1>Geoquizz</h1>
          <img style="background-color:#ADD8E6;" src="../../assets/logoMonde.png" alt="GeoQuizz">
        </div>

        <div class="col-md-6 offset-3">
          <hr>
          <h2>S'inscrire</h2>
          <br>
          <form  v-on:submit.prevent="signup({fullname, mail, password})">
            <div class="form-group">
              <input type="text" class="form-control"v-model="fullname" id="fullname"  placeholder="Nom et prénom" required>
            </div>
            <div class="form-group">
              <input type="email" class="form-control" v-model="mail" id="email"  placeholder="Mail" required>
            </div>
            <div class="form-group">
              <input type="password" class="form-control" v-model="password" id="password" placeholder="Mot de passes" required>
            </div>
            <br>
            <button type="submit" class="btn btn-block btn-primary">Sign up</button>
          </form>

          <div>
            Déjà un compte compte?
            <router-link :to="{ name: 'signin'}" class="btn btn-link" replace>Se connecter</router-link>
          </div>
        </div>
        <div class="col-md-6 offset-3">
          <br>
          <p class="lead">Le seul jeu qui te feras passer de bon moment. Enfin, un moyen de mélanger deux de tes passions, la geographie et le jeu.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { mapActions } from 'vuex'

  export default{
    data () {
      return{
        fullname: '',
        mail: '',
        password: '',
      }
    },
     methods: {
      signup(info){
        this.$store.dispatch('account/signup', info)
      }
    }
  }
</script>
<style>
  #app{
    margin: 0!important;
  }

  .title{
    color: white!important;
  }

  .jumbotron{
    margin-bottom: 0!important;
  }

  .vertical-center {
    min-height: 100%!important;
    min-height: 100vh!important;
    background: #ADD8E8!important;

    display: flex!important;
    align-items: center!important;
  }

  .signup{
    color: white!important;
  }
</style>
