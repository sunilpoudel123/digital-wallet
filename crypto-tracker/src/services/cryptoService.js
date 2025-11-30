import {apiClient} from "../utils/apiClient";

export async function getMarketData() {

    const response = await apiClient.get('/coins/markets', {

        params : {
            vs_currency: 'usd',
            order: 'market_cap_desc',
            per_page: 10,
            page: 1,
            sparkline: false
        }
    });
    return response.data;
}

export async function getCoinById(id){
    const response = await apiClient.get(`/coins/${id}`);
    return response.data;
}