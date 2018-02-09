<template>
  <b-navbar toggleable="md" type="dark">

  <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

  <b-navbar-brand>Geoquizz</b-navbar-brand>

  <b-collapse is-nav id="nav_collapse">

    <b-navbar-nav>
      <b-nav-item>
      <router-link :to="{ name: 'series'}">Séries</router-link>
      </b-nav-item>
      
      <b-nav-item>
      <router-link :to="{ name: 'createSerie'}">Créer une série</router-link>
      </b-nav-item>

      
    </b-navbar-nav>

    <!-- Right aligned nav items -->
    <b-navbar-nav class="ml-auto">
      <b-button variant="link" v-on:click="signOut()">Se deconnecter</b-button>
    </b-navbar-nav>

  </b-collapse>
</b-navbar>
</template>

<script>
import router from '@/router'
  export default {
    data (){
      return {

      }
    },
    methods: {
      signOut(){
        sessionStorage.clear()
        router.push({name: 'home'})
      }
    }
  }
</script>

<style>
.navbar{
  background-color: #ADD8E6!important;
}
</style>