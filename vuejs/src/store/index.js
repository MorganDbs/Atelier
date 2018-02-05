import Vue from 'vue'
import Vuex from 'vuex'
import api from '@/services/api'

import router from "@/router"
import createPersistedState from 'vuex-persistedstate'
import { createFlashStore } from 'vuex-flash'

Vue.use(Vuex)

export default new Vuex.Store({
	plugins: [
		createPersistedState(),
		createFlashStore()
	],
	modules: { },
	state: { },
	mutations: { },
	getters: { },
	actions: { }
})
