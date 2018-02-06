import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import GameIndex from '@/components/Game/Index'

Vue.use(Router)

export default new Router({
	routes: [
	{
		path: '/',
		name: 'index',
		component: Index
	},
	{
		path: '/game/index',
		name: 'game_index',
		component: GameIndex
	}
	]
})
