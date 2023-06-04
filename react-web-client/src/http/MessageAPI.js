
import {$host} from "./index";

export const fetchMessages = async () => {
    const {data} = await $host.get('messages')
    return data
}

export const fetchOneMessage = async (id) => {
    const {data} = await $host.get('messages/' + id)
    return data
}

export const fetchPicture = async (id) => {
    const {data} = await $host.get('picture/' + id)
    return data
}

export const generate = async (message, batchSize, numSteps, seed) => {
    const {data} = await $host.post('generate', {message, batchSize, numSteps, seed })
    return data
}