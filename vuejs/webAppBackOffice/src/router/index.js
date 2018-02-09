import Vue from 'vue'
import Router from 'vue-router'
import signin from '@/components/Account/Signin'
import signup from '@/components/Account/Signup'
import createSerie from '@/components/Series/CreateSerie'
import series from '@/components/Series/Index.vue'
import edit from '@/components/Series/Edit.vue'
import store from '@/store/index'

Vue.use(Router)

const router =  new Router({
  routes: [
    {
      path: '/signin',
      name: 'signin',
      component: signin
    },
    {
      path: '/signup',
      name: 'signup',
      component: signup
    },
    {
      path: '/createSerie',
      name: 'createSerie',
      component: createSerie
    },
    {
      path: '/series',
      name: 'series',
      component: series,
    },
    {
      path: '/:serie_id',
      name: 'edit',
      component: edit
    }
  ]
})

router.beforeEach((to, from, next) => {
    if(to.name != 'signin' && to.name != 'signup' && ! store.getters['account/isConnected']) {
      next({name: 'signin'})
    }
    else if(to.name == 'signin' && store.getters['account/isConnected']) {
      next({name: 'home'})
    }
    else {
      next()
    }
  })


export default router
