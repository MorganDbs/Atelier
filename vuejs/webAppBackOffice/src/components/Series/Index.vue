<template>
<div>
	<navbar></navbar>
	<b-jumbotron header="Geoquizz" lead="Liste des séries" >
            <img style="background-color:#ADD8E6; border-radius: 2% 2%;" src="../../assets/logoMonde.png" alt="CO-OP">
	</b-jumbotron>
	<b-container>
		<b-row>
			<b-card-group deck>
				<b-card v-for="serie in series" :key="serie.id">
				<h3>
				{{serie.name}} <small> ({{serie.city}})</small>
				</h3>
			    <p class="card-text">
			      {{serie.description}}
			    </p>
			    <div slot="footer">
	                <router-link :to="{ name: 'edit', params: { serie_id: serie.id }}">Ajouter des images a cette serie</router-link>
	            </div>   
			  </b-card>
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