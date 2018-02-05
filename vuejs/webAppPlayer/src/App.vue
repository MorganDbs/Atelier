<template>
	<div>
		<navbar></navbar>
		<router-view/>
	</div>
</template>

<script>
	import Navbar from '@/components/app/Navbar'

	export default {
		name: 'App',
		components: {
			navbar: Navbar
		}
	}
</script>