<template>
	<div class="wrapper">
		<v-map id="map" ref="serie" :zoom="difficulty.zoom" :min-zoom="difficulty.zoom" :max-zoom="difficulty.zoom" :options="{zoomControl: false}" :center="serie.coords" v-on:l-click="addMarker">
			<v-tilelayer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"></v-tilelayer>
			<v-marker v-if="marker" :lat-lng="marker.position" :visible="true" :draggable="false" :icon="pinIcon">
			</v-marker>
			<v-marker v-if="serie" v-for="picture in pictures" :key="picture.id" :lat-lng="picture.coords" :visible="true" :draggable="false" :icon="markerIcon">
			</v-marker>
		</v-map>
		
		<div class="board">
			<div class="timer">
				<vue-circle
        			:progress="100"
        			:size="100"
        			:reverse="false"
        			line-cap="round"
        			:fill="timer.fill"
        			empty-fill="rgba(0, 0, 0, 0.3)"
        			:animation="{ duration: (difficulty.multipliers[multiplier].time * 1000) }"
        			:start-angle="0"
        			insert-mode="append"
        			:thickness="5"
        			:show-percent="false"
        			@vue-circle-end="timer_finished"
        			class="circle-timer">
        			<p class="multiplier">x{{ difficulty.multipliers[multiplier].multiplier }}</p>
				</vue-circle>
			</div>
			<div class="score">
				<h5>Score</h5>
				<p>{{ score }}</p>
			</div>
		</div>
	</div>
</template>

<script>
	import Vue2Leaflet from 'vue2-leaflet'
	import store from '@/store'
	import VueCircle from 'vue2-circle-progress'
	import { mapGetters, mapActions, mapMutations } from 'vuex'

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
				timer: {
					fill: {
						color: '#FFC312'
					}
				},
				multiplier: 1
			}
		},
		components: {
			'v-map': Vue2Leaflet.Map,
			'v-tilelayer' :Vue2Leaflet.TileLayer,
			'v-marker': Vue2Leaflet.Marker,
			'v-group': Vue2Leaflet.LayerGroup,
			'v-popup': Vue2Leaflet.Popup,
			VueCircle
		},
		computed: {
			...mapGetters(
                {
                    difficulty: 'geoquizz/getDifficulty',
                    serie: 'geoquizz/getSerie',
                    pictures: 'geoquizz/getPictures',
                    score: 'geoquizz/getScore'
                }
            )
		},
		methods: {
			addMarker(e) {
				this.marker = { position: e.latlng }
				let picture = this.pictures[0]
				let distance = (e.latlng.distanceTo(picture.coords) / 1000).toFixed(2) // convert meter to kilometer
				console.log(`${distance} km`)
				// TODO : ajouter un timer et calculer les points selon le temps de réponse
				// TODO : afficher le score et le timer sur l'interface
				// TODO : afficher l'image sur l'interface
				// TODO : Ajouter le zoom dans le retour de l'API sur les difficultés
				// TODO : Afficher un message pour dire a quelle distance on le lieu a été place par rapport a lieu original
				// TODO : Bouton suivant pour passer à l'image suivant ?
				let a = this.difficulty.distances.find((e) => {
					return distance < e.distance
				})
				console.log("---> " + a.points)
			},
		    timer_finished(event) {
		   		console.log(Object.keys(this.difficulty.distances).length);
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
	.board {
		width: 270px;
		height: 125px;
		background-color: #2c3e50;
		z-index: 99;
		bottom: 40px;
		left: 40px;
		padding: 0;
		position: absolute;
	}
	.board .timer {
		width: 135px;
		height: inherit;
		padding: 10px;
		margin: 0;
		box-sizing: border-box;
		display: inline-block;
		float: left;
		background-color: #2c3e50;
	}
	.board .timer .circle-timer {
		margin: 3px 0 0 8px;
	}
	.board .timer .circle-timer .multiplier {
		color: #FFC312;
		margin-top: 3px;
		font-size: 2em;
	}
	.board .score {
		width: 135px;
		height: inherit;
		padding: 10px;
		margin: 0;
		box-sizing: border-box;
		display: inline-block;
		float: left;
		background-color: #34495e;

		color: #FFC312;
		font-size: 3em;
		text-align: center;
	}
</style>