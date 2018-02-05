<template>
	<div class="wrapper">
		<v-map id="map" ref="map" :zoom="map.zoom" :min-zoom="map.minZoom" :max-zoom="map.maxZoom" :center="map.position">
			<v-tilelayer url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"></v-tilelayer>
			<v-marker v-on:l-add="bindPopup" :key="marker.id" :lat-lng="marker.position" :visible="marker.visible" :draggable="marker.draggable" :icon="marker.icon">
				<v-popup red="popup" :popupopen="true">
					<h4>GeoQuizz</h4>
					<p>Un jeu amusant où tu dois positionner une photo sur la carte de ta ville sans te tromper et plus vite que les autres !</p>
					<router-link :to="{name: 'game_index'}" class="btn btn-success">Jouer</router-link>
				</v-popup>
			</v-marker>
		</v-map>
	</div>
</template>

<script>
	import Vue2Leaflet from 'vue2-leaflet'

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
				map: {
					minZoom: 13,
					maxZoom: 13,
					zoom: 13,
					position : {
						lat: 48.6906765, 
						lng: 6.1764079
					}
				},
				marker: { 
					position : {
						lat: 48.6906765, 
						lng: 6.1764079
					},
					draggable: true,
					visible: true,
					icon: markerIcon
				},
				markers: []
			}
		},
		components: {
			'v-map': Vue2Leaflet.Map,
			'v-tilelayer' :Vue2Leaflet.TileLayer,
			'v-marker': Vue2Leaflet.Marker,
			'v-group': Vue2Leaflet.LayerGroup,
			'v-popup': Vue2Leaflet.Popup
		},
		created() {
      		this.markers.push(this.marker)
		},
		methods: {
			alert(item) {
				alert('this is ' + JSON.stringify(item));
			},
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
</style>