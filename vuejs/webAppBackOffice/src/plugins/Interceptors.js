import Vue from 'vue'
import api from '@/configApi'
import ls from '@/plugins/ls'
import store from '@/store'

export default {
    install: (Vue, options = {}) => {
        // Add a request interceptor
        api.interceptors.request.use( function (config) {
            if(ls.get('token')){
                if(!config.params){
                    config.params = {}
                }
                config.params.token = ls.get('token')
            }
            return config
        }, function (error) {
            return Promise.reject(error)
        })

        // Add a response interceptor
        api.interceptors.response.use(function (response) {
            return response;
        }, function (error) {
            if(error.response && error.response.status == 401){
                store.dispatch('account/signout', ! error.response.data.error.indexOf("wrong token"))
                options.router.push({name: 'signin'})
            }
            return Promise.reject(error)
        })
    }
}
