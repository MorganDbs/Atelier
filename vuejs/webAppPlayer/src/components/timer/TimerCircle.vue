<template>
	<div>
		<vue-circle
			ref="timerCircle"
			:progress="100"
			:size="100"
			:reverse="false"
			line-cap="round"
			:fill="timer.fill"
			empty-fill="rgba(0, 0, 0, 0.3)"
			:animation="{ duration: (multipliers[multiplier].time * 1000) }"
			:start-angle="-1.5"
			insert-mode="append"
			:thickness="5"
			:show-percent="false"
			@vue-circle-progress="progress"
			@vue-circle-end="timerFinished">
			<p class="multiplier">x{{ multipliers[multiplier].multiplier }}</p>
		</vue-circle>
	</div>
</template>

<script>	
	import VueCircle from 'vue2-circle-progress-redraw'

	export default {
		data() {
			return {
				timer: {
					fill: {
						color: '#FFC312'
					}
				}
			}
		},
		props: ['multipliers', 'multiplier'],
		components: {
			VueCircle
		},
		methods: {
			progress(event, progress, stepValue) {
				if (stepValue == 100) {
					this.$emit('timerFinished')
				}
			},
			timerFinished(event) { },
			redraw(time) {
				this.$refs.timerCircle.redraw({
                    animation: {
                    	duration: (time * 1000)
                    }
                })
			}
		}
	}

</script>