import api from '@/configApi'
import {router} from '@/router'
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
        }
    }
}
