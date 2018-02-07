import Vue from 'vue'
import Router from 'vue-router'
import signin from '@/components/Signin'
import signup from '@/components/Signup'
import home from '@/components/Home'
import pageCo from '@/components/PageCo'
import map from '@/components/map'

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
      path: '/map',
      name: 'map',
      component: map
    },
    {
      path: '/pageCo',
      name: 'pageCo',
      component: pageCo
    },

    {
      path: '/',
      name: 'home',
      component: home
    }

  ]
})

// Les middlewares
router.beforeEach((to, from, next) => {

  if (to.name == 'pageCo' && sessionStorage.getItem("isConnected") != "Connect") {

    next({name: 'signin' })
  }
  else if (to.name == 'signin' && sessionStorage.getItem("isConnected") == "Connect" ){

    next({name: 'PageCo' })
  }
  else{
    next()
  }
})


export default router
