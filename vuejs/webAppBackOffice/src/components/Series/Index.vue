<template>
<div>
	<navbar></navbar>

	<b-container>
		<h1 class="mt-3">Liste des séries</h1>
		<hr />
		<b-row>
			<b-card-group deck class="mt-4">
				<b-col lg="4" v-for="serie in series" :key="serie.id" class="mb-5">
					<b-card class="text-center" :img-src="test+serie.id+test2+serie.picture" >
						<h3>
					{{serie.name}} <small> ({{serie.city}})</small>
					</h3>
				    <p class="card-text">
				      {{serie.description}}
				    </p>
				    <div slot="footer">
		                <router-link :to="{ name: 'edit', params: { serie_id: serie.id }}"><b-button variant="info">Ajouter des images</b-button></router-link>
		            </div>
				  </b-card>
	            </b-col> 
			</b-card-group>
		</b-row>
	</b-container>
</div>
</template>

<script>
import { mapActions } from 'vuex'
import { mapGetters } from 'vuex'
import navbar from '@/components/UI/navbar'

export default{
		components: {
            navbar: navbar
        },
        data () {
            return {
            	test: 'http://localhost:8080/geoquizz/api/series/',
        		test2: '/pictures/',
            }
        },
        created(){
            this.$store.dispatch('serie/initiateSeries')
        },
        methods: {

        },
        computed: {
            ...mapGetters({
                series: 'serie/getSeries'
            })
        }
    }
</script>

<style>

</style>