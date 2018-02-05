import localStorage from 'local-storage'

export default {
	get: (key, value = null) => {
		return localStorage(key) || value
	},
	set: (key, value) => {
		return localStorage(key, value)
	},
	remove: (key) => {
		return localStorage.remove(key)
	}
}