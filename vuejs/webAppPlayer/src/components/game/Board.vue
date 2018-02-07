<template>
	<div class="wrapper">
		<v-map id="map" ref="map" :zoom="13" :min-zoom="13" :max-zoom="13"  :center="serie.coords" v-on:l-click="addMarker">
			<v-tilelayer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"></v-tilelayer>
			<v-marker v-if="marker" :lat-lng="marker.position" :visible="true" :draggable="false" :icon="pinIcon">
			</v-marker>
			<v-marker v-if="serie" v-for="picture in serie.pictures" :key="picture.id" :lat-lng="picture.coords" :visible="true" :draggable="false" :icon="markerIcon">
			</v-marker>
		</v-map>
	</div>
</template>

<script>
	import Vue2Leaflet from 'vue2-leaflet'
	import store from '@/store'
	import { mapGetters, mapActions } from 'vuex'

	var pinIcon = L.icon({
		iconUrl: 'static/images/pin.png',
		shadowUrl: 'static/images/leaflet/marker-shadow.png',
		iconSize:     [32, 32],
		iconAnchor:   [4, 28]
	})

	var markerIcon = L.icon({
		iconUrl: 'static/images/leaflet/marker-icon.png',
		shadowUrl: 'static/images/leaflet/marker-shadow.png',
		iconSize:     [25, 41],
		iconAnchor:   [25, 41],
		popupAnchor:  [-12.5, -40]
	})

	export default {
		data() {
			return {
				pinIcon: pinIcon,
				markerIcon: markerIcon,
				marker: null,
				serie: {
			        id: "cfd653ef-002e-4bf6-85ef-0217f1aef892",
			        name: "th",
			        city: "th",
			        description: "Un quizz sur la ville de Nancy",
			        coords: {
			            lat: 48.6884439,
			            lng: 6.1764079
			        },
			        pictures: [
			            {
			            	id: 1,
			                picture: "https://www.petitfute.com/medias/professionnel/30049/premium/600_450/223989-nancy-place-stanislas.jpg",
			                coords: {
			                    lat: 48.6936184,
			                    lng: 6.1832413
			                }
			            },
			            {
			            	id: 2,
			                picture: "http://www.arte-charpentier.com/wp-content/uploads/2017/02/Ar_EQU_Nancy_gare_Laurent_Durand_Exterieur_Exterieur-32.jpg",
			                coords: {
			                    lat: 48.6891985,
			                    lng: 6.1737367
			                }
			            }
			        ]
			    }
			}
		},
		components: {
			'v-map': Vue2Leaflet.Map,
			'v-tilelayer' :Vue2Leaflet.TileLayer,
			'v-marker': Vue2Leaflet.Marker,
			'v-group': Vue2Leaflet.LayerGroup,
			'v-popup': Vue2Leaflet.Popup
		},
		computed: {
			...mapGetters(
                {
                    difficulty: 'geoquizz/getDifficulty',
                    // serie: 'geoquizz/getSerie'
                }
            )
		},
		methods: {
			addMarker(e) {
				this.marker = { position: e.latlng }
				let picture = this.serie.pictures[0]
				let distance = (e.latlng.distanceTo(picture.coords) / 1000).toFixed(2) // convert meter to kilometer
				console.log(`${distance} km`)
				// TODO : calcul des points selon la distance
				// TODO : ajouter un timer et calculer les points selon le temps de réponse
				// TODO : afficher le score et le timer sur l'interface
				// TODO : afficher l'image sur l'interface
				// TODO : Ajouter le zoom dans le retour de l'API sur les difficultés
				// TODO : Afficher un message pour dire a quelle distance on le lieu a été place par rapport a lieu original
				// TODO : Bouton suivant pour passer à l'image suivant ?
			}
		}
	}

</script>

<style>
	.wrapper {
		min-height: calc(100vh - 56px);
	}
	.wrapper #map {
		overflow: none;
		width: 100vw;
		height: calc(100vh - 56px);
		z-index: 1;
		cursor: pointer;
	}
	.leaflet-control-container {
		display: none;
	}
</style>