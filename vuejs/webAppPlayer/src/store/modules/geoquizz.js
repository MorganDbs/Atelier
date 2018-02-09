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
		},
		scores: null
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
		},
		setScores: (state, scores) => {
			state.scores = scores
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
		},
		getScores: (state) => {
			return state.scores
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
				data.difficulty.distances.sort((a, b) =>  {
					return (a.id_distance > b.id_distance) ? 1 : (
						(b.id_distance > a.id_distance) ? -1 : 0
					);
				});
				data.difficulty.multipliers.sort((a, b) =>  {
					return (a.id_multiplier > b.id_multiplier) ? 1 : (
						(b.id_multiplier > a.id_multiplier) ? -1 : 0
					);
				});
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
			api.put('/games?token=' + state.game.token, {
				score: `${state.game.score}`
			})
			.then((response) => {

			})
			.catch((error) => {
				console.log(error)
			})
		},
		getScores: ({commit}, serie_id) => {
			api.get(`/series/${serie_id}/games`)
			.then((response) => {
				commit('setScores', response.data.games)
			})
			.catch((error) => {
				console.log(error)
			})
		}
	}
}