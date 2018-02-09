<template>
	<b-container>
		<b-row class="justify-content-md-center">
			<b-col lg="10" md="12" sm="12">
				<h1 class="mt-4">Scores</h1>
			</b-col>
			<b-col lg="10" md="12" sm="12">
				<b-table hover :items="scores" :fields="fields" class="mt-4"></b-table>
			</b-col>
		</b-row>
	</b-container>
</template>

<script>
	import store from '@/store'
	import router from '@/router'
	import { mapGetters, mapActions } from 'vuex'

	export default {
		data() {
			return {
				fields: [
					{
						key: 'nickname',
						label: 'Pseudonyme'
					},
					{
						key: 'difficulty',
						label: 'Difficulté'
					},
					{
						key: 'score',
						label: 'Score',
						sortable: true
					}
				]
			}
		},
		created() {
			let serie_id = router.history.current.params.id
			store.dispatch('geoquizz/getScores', serie_id)
		},
		computed: {
			...mapGetters({
				scores: 'geoquizz/getScores'
			})
		}
	}

</script>