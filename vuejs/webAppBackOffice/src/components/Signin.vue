<template>
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
      </div>
      <hr/>

    </div>
 <div class="bloc">
    <h1>GeoQuizz Sign in</h1>
      <form @submit.prevent="signin()">
      <label for="email">Email</label>
      <input type="email" v-model="members.mail" id="email" name="email" placeholder="Votre email .." required>

      <label for="password">Password</label>
      <input type="password" v-model="members.password" id="password" name="password" placeholder="Votre mot de passe.." required>

      <input type="submit" value="Submit">
    </form>
 </div>
  </div>
</template>

<script>
  import configApi from '../configApi'
  import router from '../router'
export default {
  name: 'signin',
  data () {
    return {
      members:{mail: '',
        password:''
      }

    }
  },
  methods: {
    signin(){
      console.log(this.members)
      configApi.post('/signin', this.members).then((response)=> {

        sessionStorage.setItem("isConnected", "Connect")
        sessionStorage.setItem("token", response.data.token)
        sessionStorage.setItem("id", response.data.id_users)
        router.push("PageCo")
      })
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
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #ADD8E8;
}

div {
  border-radius: 5px;
  background-color: white;
  padding: 20px;
}
.bloc{
  background-color: #f2f2f2;

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
</style>
