import api from '@/configApi'
import router from '@/router'
import Vue from 'vue'

export default {
    namespaced: true,
    state: {
        series: null,
        current_serie: null
    },
    mutations: {
        initiateSeries: (state, data) => {
            state.series = data.series
        },
        currentSerie: (state, data) => {
            state.current_serie = data
        },
        uploadSerie: (state) =>{
            router.push({
                name: "series"
            })
        }
    },
    getters: {
        getSeries: (state) => {
            return state.series
        },
        getCurrentSerie: (state) => {
            return state.current_serie
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
        }
    }
}
