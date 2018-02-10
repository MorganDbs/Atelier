import Vue from 'vue'
import Vuex from 'vuex'
import api from '@/configApi'
import router from "@/router"
import serie from '@/store/modules/serie'
import account from '@/store/modules/account'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
    plugins: [createPersistedState()],
    modules: {
        serie,
        account
    },
    state: { },
    mutations: { },
    getters: { },
    actions: { }
})
