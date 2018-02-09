<template>
	<b-container fluid>
		<b-row class="justify-content-md-center">
			<b-col lg="10" md="6" sm="12">
				<flash-message class="mt-4" variant="success"></flash-message>
				<h1 class="mt-4">GeoQuizz</h1>
				<hr>
				<b-card bg-variant="light" class="mb-3">
					<b-form-group horizontal
						class="m-0"
						label="Pseudonyme:"
						:label-cols="2"
						label-class="text-sm-right"
						label-for="nickname">
						<b-form-input id="nickname" v-model="nickname" required></b-form-input>
					</b-form-group>

					<b-form-group horizontal
						class="m-0"
						label="Difficulté:"
						:label-cols="2"
						label-class="text-sm-right"
						label-for="difficulty">
						<b-form-select id="difficulty" v-model="difficulty" class="mb-2 mt-2 mr-sm-2 mb-sm-0">
							<option slot="first" :value="null" disabled>Choisir la difficulté...</option>
							<option v-for="difficulty in difficulties" :key="difficulty.id" :value="difficulty">{{ difficulty.name }}</option>
						</b-form-select>
					</b-form-group>
				</b-card>
			</b-col>

			<b-col lg="10" md="10" sm="12">
				<b-row class="justify-content-md-center">
					<b-col sm="12" md="3" lg="4" :key="serie.id" v-for="serie in series">
						<b-card-group deck class="mb-5">
							<b-card :img-src="serie.picture" :img-alt="serie.name" img-top>
								<p class="card-text">
									<h5>{{ serie.name }}</h5>
									<p>Ville : {{ serie.city }}<br />{{ serie.description }}</p>
									<center>
											<b-button v-if="nickname && difficulty" variant="primary" class="mt-4" @click="sendGameInfo({serie, difficulty, nickname})">Jouer</b-button>
											<router-link :to="{ name: 'score_board', params: { id: serie.id } }">
												<b-button variant="success" class="mt-4">Classement</b-button>
											</router-link>
									</center>
								</p>
							</b-card>
						</b-card-group>
					</b-col>
				</b-row>
			</b-col>
		</b-row>
	</b-container>
</template>

<script>
	import { mapGetters, mapActions } from 'vuex'
	import store from '@/store'

	export default {
		data: () => {
			return {
				difficulty: null,
				nickname: null
			}
		},
		created() {
			store.dispatch('geoquizz/getGeoQuizz')
		},
		computed: {
			...mapGetters(
                {
                    series: 'geoquizz/getSeries',
                    difficulties: 'geoquizz/getDifficulties'
                }
            )
		},
		methods: {
			...mapActions('geoquizz', ['sendGameInfo'])
		}
	}

</script>