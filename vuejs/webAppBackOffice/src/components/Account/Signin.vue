<template>
  <div class="jumbotron vertical-center bg">
    <div class="container">
      <div class="row">
        <div class="col-md-2 offset-5">
          <h1>Geoquizz</h1>
          <img src="../../assets/logoMonde.png" alt="GeoQuizz">
        </div>

        <div class="col-md-6 offset-3">
          <hr>
          <h2>Se connecter</h2>
          <br>
          <form  v-on:submit.prevent="signin({mail, password})">
            <div class="form-group">
              <input type="email" class="form-control" v-model="mail" id="email"  placeholder="Mail" required>
            </div>
            <div class="form-group">
              <input type="password" class="form-control" v-model="password" id="password" placeholder="Mot de passe" required>
            </div>
            <br>
            <button type="submit" class="btn btn-block btn-primary">Se connecter</button>
          </form>

          <div>
            Toujours pas de compte?
            <router-link :to="{ name: 'signup'}" class="btn btn-link" replace>S'inscrire</router-link>
          </div>
        </div>
        <div class="col-md-6 offset-3">
          <br>
          <p class="lead">Le seul jeu qui vous fera passer, de bons moments. Enfin, un moyen de mélanger deux de vos passions, la géographie et le jeu.</p>
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
        mail: '',
        password: '',
      }
    },
    methods: {
      signin(info){
        this.$store.dispatch('account/signin', info)
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
