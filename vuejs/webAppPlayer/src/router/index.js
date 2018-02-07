import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import GameIndex from '@/components/Game/Index'
import GameBoard from '@/components/Game/Board'

Vue.use(Router)

export default new Router({
	routes: [
	{
		path: '/',
		name: 'index',
		component: Index
	},
	{
		path: '/game',
		name: 'game_index',
		component: GameIndex
	},
	{
		path: '/board',
		name: 'game_board',
		component: GameBoard
	}
	]
})
