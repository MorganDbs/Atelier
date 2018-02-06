import Vue from 'vue'
import Router from 'vue-router'
import Signin from '@/components/Signin'
import Signup from '@/components/Signup'
import Home from '@/components/Home'

Vue.use(Router)

const router =  new Router({
  routes: [
    {
      path: '/signin',
      name: 'Signin',
      component: Signin
    },
    {
      path: '/signup',
      name: 'Signup',
      component: Signup
    },

    {
      path: '/',
      name: 'Home',
      component: Home
    }

  ]
})

/** Les middlewares
router.beforeEach((to, from, next) => {
  if (to.name == 'PageCo' && sessionStorage.getItem("isConnected") != "Connect") {
    next({name: 'Signin' })
  }
  else if (to.name == 'Signin' && sessionStorage.getItem("isConnected") == "Connect" ){
    next({name: 'PageCo' })
  }
  else{
    next()
  }
})
 */

export default router
