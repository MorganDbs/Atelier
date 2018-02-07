import Vue from 'vue'
import Router from 'vue-router'
import signin from '@/components/Signin'
import signup from '@/components/Signup'
import home from '@/components/Home'
import pageCo from '@/components/PageCo'
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
  console.log('1')
  if (to.name == 'pageCo' && sessionStorage.getItem("isConnected") != "Connect") {
    console.log('2')
    next({name: 'signin' })
  }
  else if (to.name == 'signin' && sessionStorage.getItem("isConnected") == "Connect" ){
    console.log('3')
    next({name: 'PageCo' })
  }
  else{
    next()
  }
})


export default router
