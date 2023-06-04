import axios from "axios";

const $host = axios.create({
    baseURL: 'http://localhost:8080/'
})

export {
    $host
}