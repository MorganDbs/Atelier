import Vue from 'vue'
import api from '@/services/api'

export default {
	namespaced: true,
	state: { 
		series: null,
		difficulties: null,
		game: {
			serie: null,
			difficulty: null,
			nickname: null,
			score: 0,
			token: null
		}
	},
	mutations: {
		setNickname: (state, nickname) => {
			state.game.nickname = nickname
		},
		setDifficulty: (state, difficulty) => {
			state.game.difficulty = difficulty
		},
		setToken: (state, token) => {
			state.game.token = token
		},
		setSerie: (state, serie) => {
			state.game.serie = serie
		},
		setSeries: (state, series) => {
			state.series = series
		},
		setDifficulties: (state, difficulties) => {
			state.difficulties = difficulties
		},
		setScore: (state, data) => {

		}
	},
	getters: {
		getNickname: (state) => {
			return state.game.nickname
		},
		getSeries: (state) => {
			return state.series
		},
		getSerie: (state) => {
			return state.game.serie
		},
		getDifficulties: (state) => {
			return state.difficulties
		},
		getDifficulty: (state) => {
			return state.difficulty
		},
		getScore: (state) => {
			return state.score
		}
	},
	actions: {
		sendGameInfo: ({commit}, data) => {
			api.post('/serie', {
				id_serie: data.serie.id,
				id_difficulty: data.difficulty.id,
				nickname: data.nickname
			})
			.then((response) => {
				commit('setNickname', data.nickname)
				commit('setDifficulty', data.difficulty)
				commit('setSerie', response.serie)
				commit('setToken', response.token)
			})
			.catch((error) => {
				console.log(error)
			})
		},
		setScore: ({commit}, data) => {
			commit('setScore', data)
		},
		getGeoQuizz: ({commit}) => {
			api.get('/series')
			.then((response) => {
				commit('setSeries', response.series)
				commit('setDifficulties', response.difficulties)
			})
			.catch((error) => {
				console.log(error)
			})
		},
		sendScore: ({state}) => {
			api.put('/games?token=' + state.token, {
				score: state.game.score
			})
			.then((response) => {

			})
			.catch((error) => {
				console.log(error)
			})
		}
	}
}