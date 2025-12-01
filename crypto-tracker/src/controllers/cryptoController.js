import {getMarketData, getCoinById} from "../services/cryptoService";

export async function fetchMarket(req, res, next){
    try {
        const data = await getMarketData();
        res.json(data);
    } catch (err) {
        next(err);
    }
}

export async function fetchCoin(req, res, next){
    try {
        const {id} = req.params;
        const data = await getCoinById(id);
        res.json(data);
    } catch (err) {
        next(err);
    }
}

