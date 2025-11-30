import express from 'express';
import {config} from "./src/config/config";
import {errorHandler} from "./src/middleware/errorHandler";
import cryptoRoutes from "./src/routes/cryptoRoutes";

const app = express();
app.use(express.json());

app.use('/api/crypto', cryptoRoutes);

app(errorHandler);

app.listen(config.port, () => {
    console.log(`Server running on port ${config.port}`);
});

