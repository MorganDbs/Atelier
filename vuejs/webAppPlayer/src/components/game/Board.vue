<template>
	<div class="wrapper">
		<b-button @click="showModal" variant="info" class="smallPicture">
			<b-img :src="pictures[pictureIndex].picture" class="my-3"></b-img>
		</b-button>
		<b-modal ref="picture" size="lg" hide-footer title="Image">
			<div class="d-block text-center">
				<b-row class="justify-content-md-center">
					<b-col lg="12">
						<b-img :src="pictures[pictureIndex].picture" fluid></b-img>
					</b-col>
				</b-row>
			</div>
			<b-btn class="mt-3" variant="outline-danger" block @click="hideModal">Fermer</b-btn>
		</b-modal>
		<v-map id="map" ref="serie" :zoom="difficulty.zoom" :min-zoom="difficulty.zoom" :max-zoom="difficulty.zoom" :center="serie.coords" v-on:l-click="addMarker">
			<v-tilelayer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"></v-tilelayer>
			<v-marker v-if="marker" :lat-lng="marker.position" :visible="true" :draggable="false" :icon="pinIcon">
			</v-marker>
		</v-map>

		<div class="board">
			<div class="timer">
				<timer-circle ref="timerCircle" class="circle-timer" v-if="difficulty.multipliers && !endGame" @timerFinished="timerFinished" :multipliers="difficulty.multipliers" :multiplier="multiplierIndex"></timer-circle>

				<p v-if="endGame" class="circle-end-time">
					Fin du jeu !
				</p>
			</div>
			<div class="score">
				<h5>Score</h5>
				<p>{{ score }}</p>
			</div>
		</div>
	</div>
</template>

<script>
	import store from '@/store'
	import router from '@/router'
	import Vue2Leaflet from 'vue2-leaflet'
	import { mapGetters, mapActions, mapMutations } from 'vuex'
	import TimerCircle from '@/components/timer/TimerCircle'

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
				endGame: false,
				pictureIndex: 1,
				multiplierIndex: 0,
				score: 0
			}
		},
		components: {
			'v-map': Vue2Leaflet.Map,
			'v-tilelayer' :Vue2Leaflet.TileLayer,
			'v-marker': Vue2Leaflet.Marker,
			'v-group': Vue2Leaflet.LayerGroup,
			'v-popup': Vue2Leaflet.Popup,
			TimerCircle
		},
		computed: {
			...mapGetters(
				{
					difficulty: 'geoquizz/getDifficulty',
					serie: 'geoquizz/getSerie',
					pictures: 'geoquizz/getPictures',
					nickname: 'geoquizz/getNickname'
				}
			)
		},
		methods: {
			addMarker(e) {
				let pictures = (Object.keys(this.pictures).length - 1)

				if (this.pictureIndex >= pictures) {
					this.endGame = true
					let message =  `Ta partie de jeu est terminée, néanmoins tu as obtenu un score de ${this.score} points.`
					store.commit('FLASH/SET_FLASH', { 
						message: message, 
						variant: 'success' 
					})
					store.dispatch('geoquizz/sendScore')
					router.push({ name: 'game_index'})
				} else {
					this.marker = { position: e.latlng }
					let picture = this.pictures[0]
					let distance = (e.latlng.distanceTo(picture.coords) / 1000).toFixed(2) // convert meter to kilometer
					let distancesPoints = this.difficulty.distances.find((e) => {
						return distance < e.distance
					}).points

					let multiplier = this.difficulty.multipliers[this.multiplierIndex].multiplier
					
					this.score = (this.score + (distancesPoints * multiplier))

					store.commit('geoquizz/setScore', this.score)

					this.pictureIndex = (this.pictureIndex + 1)
					this.multiplierIndex = 0

					// Redraw du circle-timer avec le nouveau temps
					this.$refs.timerCircle.redraw(this.difficulty.multipliers[this.multiplierIndex].time)
				}
			},
			timerFinished(event) {
				let pictures = (Object.keys(this.pictures).length - 1)

				if (this.pictureIndex >= pictures) {
					this.endGame = true
					let message =  `Ta partie de jeu est terminée, néanmoins tu as obtenu un score de ${this.score} points.`
					store.commit('FLASH/SET_FLASH', { 
						message: message, 
						variant: 'success' 
					})
					store.dispatch('geoquizz/sendScore')
					router.push({ name: 'game_index'})
				} else {
					let multipliers = Object.keys(this.difficulty.multipliers).length
					if (this.multiplierIndex < multipliers && !this.endGame) {
						this.multiplierIndex = (this.multiplierIndex + 1)

						// Redraw du circle-timer avec le nouveau temps
						this.$refs.timerCircle.redraw(this.difficulty.multipliers[this.multiplierIndex].time)
					}
					if (this.multiplierIndex >= multipliers) {
						this.pictureIndex = (this.pictureIndex + 1)
						this.multiplierIndex = 0
					}
				}
			},
			showModal () {
				this.$refs.picture.show()
			},
			hideModal () {
				this.$refs.picture.hide()
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
	.wrapper .leaflet-control-container {
		display: none;
	}
	.wrapper .board {
		width: 270px;
		height: 125px;
		background-color: #2c3e50;
		z-index: 99;
		bottom: 40px;
		left: 40px;
		padding: 0;
		position: absolute;
	}
	.wrapper .board .timer {
		width: 135px;
		height: inherit;
		padding: 10px;
		margin: 0;
		box-sizing: border-box;
		display: inline-block;
		float: left;
		background-color: #2c3e50;
	}
	.wrapper .board .timer .circle-timer {
		margin: 3px 0 0 8px;
	}
	.wrapper .board .timer .circle-timer .multiplier {
		color: #FFC312;
		margin-top: 3px;
		font-size: 2em;
	}
	.wrapper .board .timer p.circle-end-time {
		width: 115px;
		height: inherit;
		white-space: normal;
		text-align: center;
		font-size: 2em;
		color: #FFC312;
	}
	.wrapper .board .score {
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
	.wrapper .smallPicture {
		width: 200px;
		position: absolute;
		z-index: 99;
		top: 100px;
		left: 40px;
		padding: 0px;
	}
	.wrapper .smallPicture img {
		width: 170px;
	}
</style>