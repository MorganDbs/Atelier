<template>
<div>
	<navbar></navbar>
	<b-jumbotron header="Geoquizz" lead="Liste des séries" >
            <img style="background-color:#ADD8E6; border-radius: 2% 2%;" src="../../assets/logoMonde.png" alt="CO-OP">
	</b-jumbotron>

	<b-container>
		<b-row>
			<div id="app">

		      <div class="formulaire">
		          

		          <div class="divMap">
		            <h2>Choisissez les différents points de votre serie</h2>

		            <v-map ref="map" id="map" :zoom=13 :center="[cityCoord.lat,cityCoord.lng]" v-on:l-click="onMapClick">
		              <v-tilelayer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"></v-tilelayer>
		              <v-marker :lat-lng="marker.coords" :icon="markerIcon" :visible="marker.visible" v-on:l-add="togglePopup">
		                <v-popup :height="40">
		                  <p>Vous êtes ici !</p>
		                </v-popup>
		              </v-marker>

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

		          </div>

		          <input type="submit" value="Submit">
		        </form>

		      </div>

		    </div>
		</b-row>
	</b-container>
</div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { mapActions } from 'vuex'
  import router from '@/router'
  import store from '@/store'
  import navbar from '@/components/UI/navbar'
  import axios from 'axios'
  import Vue2leaflet from 'vue2-leaflet'
  import configApi from '@/configApi'

  var markerIcon = L.icon({
    iconUrl: 'static/images/marker.png',
    shadowUrl: 'static/images/marker-shadow.png',
    iconSize:     [25, 41],
    iconAnchor:   [25, 41],
    popupAnchor:  [-12.5, -40]
  })

  export default{
    components: {
      navbar: navbar
    },
    data (){
      return {
        markerIcon:markerIcon,
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
        cityCoord:{
          lat:'48.692054',
          lng:'4.184417'
        },
        image:[],
        file: [],
        name: [],
      }

    },
    created: () => {
      store.dispatch('serie/currentSerie', router.history.current.params.serie_id)
    },
    methods: {
    	togglePopup(marker){

        marker.target.togglePopup();
      },
      onMapClick(e){
        console.log( e.latlng)
        this.markersToUpload.push({coords: e.latlng})

      },
      addImage(fieldName, fileList,k,item){
        this.imagePresent[k]=true;
        console.log(item)
        let input = {"fieldName": fieldName, "fileList": fileList}

        this.name.push(fileList)
        this.file.push(input);
        console.log("1")


        this.createImage(input.fileList[0],k)
        console.log(this.serie.serie.pictures)

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


        console.log(this.serie.serie.pictures);

      },
      createSerie(){
        console.log("2")

        const formData = new FormData();

        this.file.forEach(function(e){
          Array
            .from(Array(e.fileList.length).keys())
            .map(x => {
              formData.append(e.fieldName, e.fileList[x], e.fileList[x].name);
            });

        })
        console.log(this.serie)
        this.serie.serie.pictures=JSON.parse(JSON.stringify(this.serie.serie.pictures))
        console.log("helloo")
        console.log(this.serie.serie.pictures)
        configApi.post('series', this.serie, {headers: { 'content-type': 'application/json' }}).then(response => {
          console.log(response.data);
          configApi.post('series/'+response.data+'/upload', formData, {headers: { 'content-type': 'multipart/form-data' }}).then(response2 => {
            console.log(response)
          }).catch(error =>{
            console.log(error)
          })
        }).catch(error =>{
          console.log(error)
        })
      },
      createImage(file,k){


        this.image[k] = new Image();
        var reader = new FileReader();
        var vm = this;

        reader.onload = (e) => {
          vm.image[k] = e.target.result;
        };
        reader.readAsDataURL(file);

      },
      getGeoloc(){
        axios.get(`https://maps.googleapis.com/maps/api/geocode/json?address=`+this.serie.serie.city+`,+FR&key=AIzaSyCdIprtWN6lsubVYIiWCkQUGNEoLj_AxDo`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.cityCoord.lat=response.data.results[0].geometry.location.lat;
            this.cityCoord.lng=response.data.results[0].geometry.location.lng;
            this.marker.visible=true;
            this.marker.coords.lat=response.data.results[0].geometry.location.lat;
            this.marker.coords.lng=response.data.results[0].geometry.location.lng;

            //ajout dans la serie
            this.serie.serie.coords.lat=response.data.results[0].geometry.location.lat.toString();
            this.serie.serie.coords.lng=response.data.results[0].geometry.location.lng.toString();

          })
          .catch(e => {
            alert(e)
          })

      }
    },
    computed: {
      ...mapGetters(
        {
          currentSerie: 'serie/getCurrentSerie'
        }
      )
    }
  }
</script>

<style>
  #map{
    width: 55vw;
    height: 70vh;
  }
</style>
