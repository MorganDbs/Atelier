<template>
	<div class="wrapper">
		<v-map id="map" ref="map" :zoom="map.zoom" :min-zoom="map.minZoom" :max-zoom="map.maxZoom" :center="position">
			<v-tilelayer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"></v-tilelayer>
			<v-marker v-on:l-add="bindPopup" :lat-lng="position" :visible="true" :draggable="false" :icon="icon">
				<v-popup>
					<center>
						<h4>GeoQuizz</h4>
					</center>
					<p>Un jeu amusant où tu dois positionner une photo sur la carte de ta ville sans te tromper et plus vite que les autres !</p>
					<router-link :to="{name: 'game_index'}" class="btn btn-success btn-block">Jouer</router-link>
				</v-popup>
			</v-marker>
		</v-map>
	</div>
</template>

<script>
	import Vue2Leaflet from 'vue2-leaflet'
	import store from '@/store'
	import { mapGetters, mapActions } from 'vuex'

	var markerIcon = L.icon({
		iconUrl: 'static/images/leaflet/marker-icon.png',
		shadowUrl: 'static/images/leaflet/marker-shadow.png',
		iconSize:     [25, 41],
		iconAnchor:   [25, 41],
		popupAnchor:  [-12.5, -40]
	})

	export default {
		name: 'HelloWorld',
		data() {
			return {
				icon: markerIcon,
				map: {
					minZoom: 13,
					maxZoom: 13,
					zoom: 13
				},
				position: {
					lat: 48.6884439,
					lng: 6.1764079
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
		methods: {
			bindPopup(marker) {
				marker.target.togglePopup()
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
	}
	.leaflet-container a {
		color: #fff;
	}
	.leaflet-popup-close-button, .leaflet-control-container {
		display: none;
	}
</style>