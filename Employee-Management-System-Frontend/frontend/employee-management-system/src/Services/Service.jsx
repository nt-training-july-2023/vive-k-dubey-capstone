import axios from 'axios';
import { BASE_URL } from './url';

export function postRequest(url, payload) {
    return (
        axios.post(BASE_URL + url, payload)
    );
}

export function postRequestWithoutPayload(url) {
    return (
        axios.post(BASE_URL + url)
    );
}

export function getRequest(url) {
    return (
        axios.get(BASE_URL + url)
    );
}

export function putRequest(url, payload) {
    return (
        axios.put(BASE_URL + url, payload)
    );
}

export function putRequestWithoutPayload(url) {
    return (
        axios.put(BASE_URL + url)
    );
}



export function deleteRequest(url) {
    return (
        axios.delete(BASE_URL + url)
    );
}