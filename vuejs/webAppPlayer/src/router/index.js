import Vue from 'vue'
import store from '@/store'
import Router from 'vue-router'
import Index from '@/components/Index'
import GameIndex from '@/components/game/Index'
import GameBoard from '@/components/game/Board'
import ScoreBoard from '@/components/score/Index'

Vue.use(Router)

const router = new Router({
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
	},
	{
		path: '/score/:id',
		name: 'score_board',
		component: ScoreBoard
	}
	]
})

router.beforeEach((to, from, next) => {
	if (to.name === 'game_board' && !store.getters['geoquizz/getToken']) {
		next({ name: 'game_index' })
	} else {
		next()
	}
})
export default router