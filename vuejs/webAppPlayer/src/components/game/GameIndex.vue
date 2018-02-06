<template>
	<b-container fluid>
		<b-row class="justify-content-md-center">
			<b-col lg="10" md="6" sm="12">
				<h1 class="mt-4">GeoQuizz</h1>
				<hr>
				<b-card bg-variant="light" class="mb-3">
					<b-form-group horizontal
						class="m-0"
						label="Pseudonyme:"
						:label-cols="2"
						label-class="text-sm-right"
						label-for="nickname">
						<b-form-input id="nickname" required></b-form-input>
					</b-form-group>

					<b-form-group horizontal
						class="m-0"
						label="Difficulté:"
						:label-cols="2"
						label-class="text-sm-right"
						label-for="difficulty">
						<b-form-select id="difficulty" v-model="selected" class="mb-2 mt-2 mr-sm-2 mb-sm-0">
							<option slot="first" :value="null" disabled>Difficulté...</option>
							<option v-for="difficulty in difficulties" value="difficulty.id">{{ difficulty.name }}</option>
						</b-form-select>
					</b-form-group>
				</b-card>
			</b-col>

			<b-col lg="10" md="10" sm="12">
				<b-row class="justify-content-md-center">
					<b-col sm="12" md="3" lg="4" :key="serie.id" v-for="serie in series">
						<b-card-group deck class="mb-5">
							<b-card :img-src="serie.img" :img-alt="serie.name" img-top>
								<p class="card-text">
									<h5>{{ serie.name }}</h5>
									Ville : {{ serie.city }}<br />{{ serie.description }}
									<b-button variant="primary" class="mt-4 btn-block">Jouer</b-button>
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
	
	export default {
		data() {
			return {
				selected: null,
				series: [
					{
						id: "abcdefgh-0123-8901-4567-abcdefghijklmnop",
						name: "Nancy",
						city: "Nancy",
						description: "Découvrez les endroits clés de Nancy",
						img: "http://via.placeholder.com/350x150"
					},
					{
						id: "abcdefgh-0123-8901-4567-abcdefghijklmnop",
						name: "Paris",
						city: "Paris",
						description: "Découvrez les endroits clés de Paris",
						img: "http://via.placeholder.com/350x150"
					},
					{
						id: "abcdefgh-0123-8901-4567-abcdefghijklmnop",
						name: "Paris",
						city: "Paris",
						description: "Découvrez les endroits clés de Paris",
						img: "http://via.placeholder.com/350x150"
					},
					{
						id: "abcdefgh-0123-8901-4567-abcdefghijklmnop",
						name: "Paris",
						city: "Paris",
						description: "Découvrez les endroits clés de Paris",
						img: "http://via.placeholder.com/350x150"
					},
					{
						id: "abcdefgh-0123-8901-4567-abcdefghijklmnop",
						name: "Paris",
						city: "Paris",
						description: "Découvrez les endroits clés de Paris",
						img: "http://via.placeholder.com/350x150"
					},
					{
						id: "abcdefgh-0123-8901-4567-abcdefghijklmnop",
						name: "Paris",
						city: "Paris",
						description: "Découvrez les endroits clés de Paris",
						img: "http://via.placeholder.com/350x150"
					},
					{
						id: "abcdefgh-0123-8901-4567-abcdefghijklmnop",
						name: "Paris",
						city: "Paris",
						description: "Découvrez les endroits clés de Paris",
						img: "http://via.placeholder.com/350x150"
					},
					{
						id: "abcdefgh-0123-8901-4567-abcdefghijklmnop",
						name: "Paris",
						city: "Paris",
						description: "Découvrez les endroits clés de Paris",
						img: "http://via.placeholder.com/350x150"
					},
					{
						id: "abcdefgh-0123-8901-4567-abcdefghijklmnop",
						name: "Paris",
						city: "Paris",
						description: "Découvrez les endroits clés de Paris",
						img: "http://via.placeholder.com/350x150"
					}
				],
				difficulties: [
					{
						id: "abcdefgh-0123-4567-8901-abcdefghijklmnop",
						name: "Facile"
					},
					{
						id: "abcdefgh-0123-4567-8901-abcdefghijklmnop",
						name: "Moyen"
					},
					{
						id: "abcdefgh-4567-0123-8901-abcdefghijklmnop",
						name: "Expert"
					}
				]
			}
		}
	}

</script>