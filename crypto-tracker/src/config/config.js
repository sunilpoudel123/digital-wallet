import dotenv from 'dotenv';

dotenv.config();

export const config = {
    port: process.env.PORT || 5000,
    coingeckoBaseURL: process.env.COINGECKO_BASE
};

