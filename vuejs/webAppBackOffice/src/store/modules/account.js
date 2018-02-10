import api from '@/configApi'
import ls from '@/plugins/ls'
import router from '@/router'

const initialState = {
    connected: false,
    user: {}
}

export default {
    namespaced: true,
    state: {
        connected: false,
        user: {},
    },
    mutations: {
        signin: (state, data) => {
            state.connected = true
            state.user = data
            ls.set('token', data.token)
            router.push({
                name: 'series'
            })
        },
        initState(state) {
            ls.remove('token')
            Object.assign(state, initialState)
            router.push({
                name: 'signin'
            })
        }
    },
    getters: {
        isConnected: (state) => {
            return state.connected
        },
        getConnectedUser (state) {
            return state.user
        }
    },
    actions: {
        signin: ({ commit }, user) => {
            api.post('/signin', user).then((response) => {
                commit('signin', response.data)
            }).catch((error) =>{
                let e = error.response.data.error
                commit('errorSignin', e)
            })
        },
        signup: ({ commit }, user) => {
            api.post('/signup', user).then((response) => {
                api.post('/signin', user).then((response) => {
                  commit('signin', response.data)
                })
            }).catch((error) =>{
                    let e = error.response.data.error[0][0]
                    commit('errorSignup', e)
                })
        },
        signout: ({ commit }) => {
            commit("initState")
        }
    }
}
