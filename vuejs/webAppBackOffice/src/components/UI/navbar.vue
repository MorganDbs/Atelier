<template>
  <b-navbar toggleable="md" type="dark" variant="info">

  <b-navbar-brand><img src="../../assets/logoMonde.png" alt="Geoquizz" width="30px;"> Geoquizz</b-navbar-brand>
  
  <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

  <b-collapse is-nav id="nav_collapse">

    <b-navbar-nav>
      <b-nav-item class="nav-item d-inline-block" @click="goToSeries()">Séries</b-nav-item>
      <b-nav-item class="nav-item" @click="goToNewSerie()">Créer une série</b-nav-item>
    </b-navbar-nav>

    <b-navbar-nav class="ml-auto d-inline-block">
      <b-button variant="danger" @click="signout()">Se déconnecter</b-button>
    </b-navbar-nav>

  </b-collapse>
</b-navbar>
</template>

<script>
import router from '@/router'
import { mapActions } from 'vuex'

  export default {
    data (){
      return {

      }
    },
    methods: {
      ...mapActions('account', ['signout']),
      goToSeries() {
        router.push({name: 'series'})
      },
      goToNewSerie() {
        router.push({name: 'createSerie'})
      }
    }
  }
</script>

<style>
  
</style>