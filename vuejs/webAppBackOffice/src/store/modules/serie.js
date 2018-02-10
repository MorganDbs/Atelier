import api from '@/configApi'
import router from '@/router'
import Vue from 'vue'

export default {
    namespaced: true,
    state: {
        series: null,
        current_serie: null,
        city_new_serie_coords: {lat: "48.692054", lng: "6.184417"},
        currentSeriePictures: []
    },
    mutations: {
        initiateSeries: (state, data) => {
            state.series = data.series
        },
        currentSerie: (state, data) => {
            state.current_serie = data
            state.currentSeriePictures = data.pictures
        },
        uploadSerie: (state) =>{
            router.push({
                name: "series"
            })
        },
        geolocInput: (state, data) =>{
            state.city_new_serie_coords = data.results[0].geometry.location
        }
    },
    getters: {
        getSeries: (state) => {
            return state.series
        },
        getCurrentSerie: (state) => {
            return state.current_serie
        },
        getGeolocInput: (state) =>{
            return state.city_new_serie_coords
        },
        getCurrentSeriePictures: (state) =>{
            return state.currentSeriePictures
        }
    },
    actions: {
        initiateSeries: ({ commit }) => {
            api.get('/series').then((response) => {
                commit('initiateSeries', response.data)
            })
        },
        currentSerie({ commit }, id) {
            api.get('/series/' + id).then((response) => {
                commit('currentSerie', response.data)
            })
        },
        addPictureToSerie({ commit, state }, data) {
            api.put('series/'+state.current_serie.id, data.json, {headers: { 'content-type': 'application/json' }}).then(response => {
                   api.put('series/'+response.data+'/upload', data.img, {headers: { 'content-type': 'multipart/form-data' }}).then(response2 => {
                        commit('uploadSerie', response.data)
                    }).catch(error =>{
                        console.log(error)
                    })
            }).catch(error =>{
                    console.log(error)
            })      
        },
        createSerie({ commit, state }, data) {
            data.json.serie.coords.lat = state.city_new_serie_coords.lat.toString()
            data.json.serie.coords.lng = state.city_new_serie_coords.lng.toString()
            api.post('series/', data.json, {headers: { 'content-type': 'application/json' }}).then(response => {
                   api.post('series/'+response.data+'/upload', data.img, {headers: { 'content-type': 'multipart/form-data' }}).then(response2 => {
                        commit('uploadSerie', response.data)
                    }).catch(error =>{
                        console.log(error)
                    })
            }).catch(error =>{
                    console.log(error)
            })      
        },
        geolocInput({commit}, city){
            api.get(`https://maps.googleapis.com/maps/api/geocode/json?address=`+city+`,+FR&key=AIzaSyCdIprtWN6lsubVYIiWCkQUGNEoLj_AxDo`)
              .then(response => {
                commit('geolocInput', response.data)
              })
              .catch(e => {
                console.log(e)
              })
        }
    }
}
