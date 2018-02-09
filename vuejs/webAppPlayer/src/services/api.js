import axios from 'axios'
import config from '@/config/config'

export default axios.create({
	baseURL: config.url,
	headers: {
		'Accept': 'application/json',
		'Content-Type': 'application/json'
	}
})
