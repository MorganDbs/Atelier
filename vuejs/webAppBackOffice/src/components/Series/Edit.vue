<template>
<div>
	<navbar></navbar>

	<b-container>
    <h1 class="mt-3">Ajouter des images</h1>      
    <hr />
		<b-row>
			<b-col lg="12" sm="12" md="12" class="mt-4">
				    <form v-on:submit.prevent="updateSerie()">
		            <v-map ref="map" id="map" :zoom=13 :center="[currentSerie.lat,currentSerie.lng]" v-on:l-click="onMapClick">
		              <v-tilelayer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"></v-tilelayer>
                  <v-marker :lat-lng="currentSerie" :icon="markerIcon" visible v-on:l-add="togglePopup">
                    <v-popup :height="40" class="text-center">
                      <p>Cliquez sur la carte pour choisir les diférents points de la série.</p>
                      <hr />
                      <p>Vous êtes ici !</p>
                    </v-popup>
                  </v-marker>

                  <v-marker v-for="picturers in currentSerie.pictures" :lat-lng="picturers.coords" :icon="markerIcon" visible v-on:l-add="togglePopup">
                    <v-popup :height="40" class="text-center">
                      <img class="imageUpload" :src="test+currentSerie.id+test2+picturers.picture" />
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
                <br>
                <div v-if="d">L'image que vous venez d'ajouter existe deja dans la serie.</div>
		          <b-button type="submit" value="Submit" variant="info" class="btn-block mt-2" :disabled="d">Envoyer</b-button>
		        </form>
		    </b-col>
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
  import Vue2leaflet from 'vue2-leaflet'

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

  export default{
    components: {
      navbar: navbar
    },
    filters: {
      parse: function (value) {
        return value.substring(37, 456)
      }
    },
    data (){
      return {
        test: 'http://localhost:8080/geoquizz/api/series/',
        test2: '/pictures/',
        markerIcon:markerIcon,
        markersToUpload:[],
        marker:{
          visible:true,
          coords: {
            lat: '',
            lng: ''
          },
        },
        imagePresent:[],
        picturesTab: {
        	"pictures" :[]
        },
        image:[],
        file: [],
        name: [],
        d: false
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
        this.markersToUpload.push({coords: e.latlng})
      },
      addImage(fieldName, fileList,k,item){
        let input = {"fieldName": fieldName, "fileList": fileList}
        let b = false
        this.currentSeriePictures.forEach(e =>{
          if(fileList[0].name == e.picture.substring(37, 456)){
            b = true
          }
        })
        if(b){
          this.d = true
        }else{
          this.imagePresent[k]=true;
          
          this.name.push(fileList)
          this.file.push(input)
          this.createImage(input.fileList[0],k)
          this.picturesTab.pictures.push(
            {
              "img":input.fileList[0].name,
              "coords":
                {
                  "lat":item.coords.lat.toString(),
                  "lng":item.coords.lng.toString()
                }
            }
          )
        }
      },
      updateSerie(){
        const formData = new FormData();
        this.file.forEach(function(e){
          Array
            .from(Array(e.fileList.length).keys())
            .map(x => {
              formData.append(e.fieldName, e.fileList[x], e.fileList[x].name);
            });
        })
        this.picturesTab=JSON.parse(JSON.stringify(this.picturesTab))
        this.$store.dispatch('serie/addPictureToSerie', {"json": this.picturesTab, "img": formData})
      },
      createImage(file,k){
        this.image[k] = new Image();
        let reader = new FileReader();
        let vm = this;
        reader.onload = (e) => {
          vm.image[k] = e.target.result;
        };
        reader.readAsDataURL(file);
      }
    },
    computed: {
      ...mapGetters(
        {
          currentSerie: 'serie/getCurrentSerie',
          currentSeriePictures: 'serie/getCurrentSeriePictures',
          series: 'serie/getSeries'
        }
      )
    }
  }
</script>

<style>
  #map{
    width: 100%;
    height: 65vh;
  }
  .imageUpload{
    width: 150px!important;
    height: auto;
  }
</style>
