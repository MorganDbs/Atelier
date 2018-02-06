export default {
	difficulties : [
		{
			name: 'Facile',
			distances: [
				{
					points: 5,
					distance: 10
				},
				{
					points: 3,
					distance: 20
				},
				{
					points: 1,
					distance: 30
				}
			],
			multipliers: [
				{
					time: 5,
					multiplier: 4
				},
				{
					time: 10,
					multiplier: 2
				},
				{
					time: 20,
					multiplier: 1
				}
			]
		},
		{
			name: 'Moyen',
			distances: [
				{
					points: 5,
					distance: 8
				},
				{
					points: 3,
					distance: 13
				},
				{
					points: 1,
					distance: 18
				}
			],
			multipliers: [
				{
					time: 3,
					multiplier: 4
				},
				{
					time: 8,
					multiplier: 2
				},
				{
					time: 13,
					multiplier: 1
				}
			]
		},
		{
			name: 'Expert',
			distances: [
				{
					points: 5,
					distance: 5
				},
				{
					points: 3,
					distance: 8
				},
				{
					points: 1,
					distance: 13
				}
			],
			multipliers: [
				{
					time: 2,
					multiplier: 4
				},
				{
					time: 5,
					multiplier: 2
				},
				{
					time: 8,
					multiplier: 1
				}
			]
		}
	]
}