<template>
	<b-container fluid>
		<b-row class="justify-content-md-center">
			<b-col cols="10">
				<h1 class="mt-5">GeoQuizz</h1>
				<hr>
				<b-card bg-variant="light" class="mb-3">
					<b-form-group horizontal
						class="m-0"
						label="Pseudonyme:"
						:label-cols="2"
						label-class="text-sm-right"
						label-for="nickname">
						<b-form-input id="nickname"></b-form-input>
					</b-form-group>
				</b-card>
				<b-card-group deck class="mb-5">
					<b-card v-for="serie in series" :key="serie.id" :img-src="serie.img" :img-alt="serie.name" img-top>
						<p class="card-text">
							<h5>{{ serie.name }}</h5>
							Ville : {{ serie.city }}<br />{{ serie.description }}
						</p>
					</b-card>
				</b-card-group>
			</b-col>
		</b-row>
	</b-container>
</template>

<script>
	
	export default {
		data() {
			return {
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