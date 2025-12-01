import axios from 'axios';
import {config} from '../config/config.js';

export const apiClient = axios.create({
    baseURL: config.coingeckoBaseURL,
    timeout: 5000
});

