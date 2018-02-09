<template>
  <div>
    <navbar></navbar>
    <b-container>
      <h1 class="mt-3">Créer une série</h1>
      <hr/>
      <b-row>
        <b-col lg="5" sm="5" md="5">
            <b-form @submit.prevent="createSerie()">
              <b-form-group id="exampleInputGroup1"
                            label="Nom de la série:"
                            label-for="exampleInput1">
                <b-form-input id="exampleInput1"
                              type="text"
                              v-model="serie.serie.name"
                              required
                              placeholder="Entrer le nom de la série">
                </b-form-input>
              </b-form-group>
              <b-form-group id="exampleInputGroup2"
                            label="Description:"
                            label-for="exampleInput2">
                <b-form-input id="exampleInput2"
                              type="text"
                              v-model="serie.serie.description"
                              required
                              placeholder="Entrer description">
                </b-form-input>
              </b-form-group>
              <b-form-group id="exampleInputGroup2"
                            label="Ville:"
                            label-for="exampleInput2">
                <b-form-input @change="getGeoloc" id="exampleInput2"
                              type="text"
                              v-model="serie.serie.city"
                              required
                              placeholder="Entrer la ville">
                </b-form-input>
              </b-form-group>
              <b-button type="submit" variant="primary" :disabled="this.nbrPhoto < 10" class="btn-block mt-2">Ajouter</b-button>
              <br>
              <div v-if="this.nbrPhoto < 10">
                Vous devez encore ajouter {{10-this.nbrPhoto}} photos au minimum.
              </div>
              <div v-else>
                Nombre minimum de photos requises ateints.
              </div>
            </b-form>
        </b-col>
        <b-col lg="7" sm="7" md="7">
           <v-map ref="map" id="map" :zoom=13 :center="[geoloc.lat,geoloc.lng]" v-on:l-click="onMapClick">
            <v-tilelayer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"></v-tilelayer>
            <v-marker :v-if="markersToUpload" :icon="markerIcon" v-for="item,k in markersToUpload" :key="k" v-on:l-add="togglePopup" :lat-lng="item.coords">

              <v-popup >
                <p>Selectionner une image qui correspondra au point sur la carte</p>
                <div v-if="!imagePresent[k]">
                  <input name="file" type="file" :key="k" @change="addImage($event.target.name, $event.target.files,k, item)" required>
                </div>
                <div v-else>
                  <img class="imageUpload" :src="image[k]" />
                </div>
              </v-popup>
            </v-marker>
          </v-map>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
  import router from '@/router'
  import Vue2leaflet from 'vue2-leaflet'
  import { mapGetters } from 'vuex'
  import { mapActions } from 'vuex'
  import navbar from '@/components/UI/navbar'

  var marker = L.icon({
    iconUrl: 'static/images/marker.png',
    shadowUrl: 'static/images/marker-shadow.png',
    iconSize:     [35, 41],
    iconAnchor:   [25, 41],
    popupAnchor:  [-12.5, -40]
  })
  var markerIcon = L.icon({
    iconUrl: 'static/images/marker-icon.png',
    shadowUrl: 'static/images/marker-shadow.png',
    iconSize:     [25, 41],
    iconAnchor:   [25, 41],
    popupAnchor:  [-12.5, -40]
  })
  export default {
    components: {
      navbar: navbar
    },
    name: 'createSerie',
    data (){
      return {
        userMail:sessionStorage.getItem("nom"),
        markerIcon:markerIcon,
        markerIcon2:marker,
        markersToUpload:[],
        marker:{
          visible:false,
          coords: {
            lat: '',
            lng: ''
          },
        },
        imagePresent:[],
        serie: {
          serie:{
            name: '',
            description: '',
            city: '',
            coords: {
              lat: '',
              lng: ''
            },
            pictures: []
          }
        },
        image:[],
        file: [],
        name: [],
        nbrPhoto: 0
      }
    },
    created: () =>{
      this.nbrPhotos = 0
    },
    methods: {
      signOut(){
        sessionStorage.clear()
        router.push({name: 'home'})
      },
      togglePopup(marker){
        marker.target.togglePopup();
      },
      onMapClick(e){
        this.markersToUpload.push({coords: e.latlng})
      },
      addImage(fieldName, fileList,k,item){
        this.imagePresent[k]=true;
        let input = {"fieldName": fieldName, "fileList": fileList}
        this.name.push(fileList)
        this.file.push(input);
        this.createImage(input.fileList[0],k)
        this.serie.serie.pictures.push(
          {
            "img":input.fileList[0].name,
            "coords":
              {
                "lat":item.coords.lat.toString(),
                "lng":item.coords.lng.toString()
              }
          }
        )
      },
      createSerie(){
        const formData = new FormData();
        this.file.forEach(function(e){
          Array
            .from(Array(e.fileList.length).keys())
            .map(x => {
              formData.append(e.fieldName, e.fileList[x], e.fileList[x].name);
            });
        })
        this.serie.serie.pictures=JSON.parse(JSON.stringify(this.serie.serie.pictures))
        this.$store.dispatch('serie/createSerie', {"json":  this.serie, "img": formData})
      },
      createImage(file,k){
        this.image[k] = new Image();
        var reader = new FileReader();
        var vm = this;
        reader.onload = (e) => {
          vm.image[k] = e.target.result;
        };
        reader.readAsDataURL(file);
        this.nbrPhoto = this.nbrPhoto+1
      },
      getGeoloc(){
        this.$store.dispatch('serie/geolocInput', this.serie.serie.city)
      }
    },
    computed: {
      ...mapGetters(
        {
          geoloc: 'serie/getGeolocInput'
        }
      )
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #map{
    width: 100%;
    height: 65vh;
  }
</style>
