import Vue from 'vue'
import Router from 'vue-router'
import signin from '@/components/Signin'
import signup from '@/components/Signup'
import home from '@/components/Home'
import createSerie from '@/components/CreateSerie'
import series from '@/components/Series/Index.vue'
import edit from '@/components/Series/Edit.vue'

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
    },
    {
      path: '/',
      name: 'home',
      component: home
    }

  ]
})
/*
// Les middlewares
router.beforeEach((to, from, next) => {

  if (to.name == 'createSerie' && to.name == 'edit' && to.name == 'series' && sessionStorage.getItem("isConnected") != "Connect") {

    next({name: 'signin' })
  }
  else if (to.name == 'signin' && sessionStorage.getItem("isConnected") == "Connect" ){

    next({name: 'createSerie' })
  }
  else{
    next()
  }
})

*/
export default router
