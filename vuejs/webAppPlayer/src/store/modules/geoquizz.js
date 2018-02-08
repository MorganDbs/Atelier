import Vue from 'vue'
import api from '@/services/api'
import router from '@/router'

export default {
	namespaced: true,
	state: { 
		series: null,
		difficulties: null,
		game: {
			serie: null,
			pictures: null,
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
			Vue.set(state.game.difficulty, 'minZoom', difficulty.zoom)
			Vue.set(state.game.difficulty, 'maxZoom', difficulty.zoom)
		},
		setToken: (state, token) => {
			state.game.token = token
		},
		setSerie: (state, serie) => {
			state.game.serie = {
				id: serie.id,
				name: serie.name,
				city: serie.city,
				description: serie.description,
				coords: serie.coords
			}
		},
		setPictures: (state, pictures) => {
			state.game.pictures = pictures
		},
		setScore: (state, score) => {
			state.game.score = score
		},
		setSeries: (state, series) => {
			state.series = series
		},
		setDifficulties: (state, difficulties) => {
			state.difficulties = difficulties
		},
		setScore: (state, score) => {
			state.game.score = score
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
		getPictures: (state) => {
			return state.game.pictures
		},
		getDifficulties: (state) => {
			return state.difficulties
		},
		getDifficulty: (state) => {
			return state.game.difficulty
		},
		getToken: (state) => {
			return state.game.token
		},
		getScore: (state) => {
			return state.game.score
		}

	},
	actions: {
		sendGameInfo: ({commit}, data) => {			
			api.post('/games', {
				id_serie: data.serie.id,
				id_difficulty: data.difficulty.id,
				nickname: data.nickname
			})
			.then((response) => {
				commit('setNickname', data.nickname)
				commit('setDifficulty', data.difficulty)
				commit('setSerie', response.data.serie)
				commit('setPictures', response.data.serie.pictures)
				commit('setToken', response.data.token)
				router.push({ name: 'game_board' })
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
				commit('setSeries', response.data.series)
				commit('setDifficulties', response.data.difficulties)
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