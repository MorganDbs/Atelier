import axios from 'axios'

const confApi = axios.create({
  baseURL: 'http://192.168.99.100:8080/geoquizz/api',
  "headers": {
    "content-type": "application/json",
    'Accept': 'application/json',
    "Origin":'http://localhost:8080'
  }
})
export default confApi

