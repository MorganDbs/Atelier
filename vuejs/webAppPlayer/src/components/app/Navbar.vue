<template>
	<b-navbar toggleable="md" type="dark" variant="info">

		<b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

		<b-navbar-brand href="#">GeoQuizz</b-navbar-brand>

		<b-collapse is-nav id="nav_collapse">

			<b-navbar-nav>
				<b-nav-item @click="goToIndex">Accueil</b-nav-item>
			</b-navbar-nav>

		</b-collapse>
	</b-navbar>
</template>

<script>
	import router from '@/router'
	export default {
		methods: {
			goToIndex() {
				router.push({ name: 'index' })
			}
		}
	}

</script>