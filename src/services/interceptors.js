import api from '@/services/api'
import ls from '@/services/ls'
import store from '@/store/index'

export default {
	install: (Vue, options = {}) => {

		api.interceptors.request.use((config) => {
			if (ls.get('token')) {
				if (!config.params) {
					config.params = {}
				}
				config.params.token = ls.get('token')
			}
			return config
		}, (error) => {
			return Promise.reject(error)
		})

		api.interceptors.response.use((response) => {
			return response
		}, (error) => {
			if (error.response && error.response.status === 401) {
				store.dispatch('auth/logout', !error.response.data.error.indexOf('wrong token'))
				options.router.push({ name: 'login' })
			}
			return Promise.reject(error)
		})
	}
}