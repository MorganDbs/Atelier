import axios from 'axios'

const confApi = axios.create({
  baseURL: 'http://localhost:8080/geoquizz/api',

})
export default confApi

