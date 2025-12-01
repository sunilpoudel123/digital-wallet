import express from 'express';
import {fetchMarket, fetchCoin} from '../controllers/cryptoController'

const router = express.Router();

router.get('/market', fetchMarket);
router.get('/coin/:id', fetchCoin);

export default router;
