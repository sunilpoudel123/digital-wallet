require('dotenv').config();
const express = require('express');
const helmet = require('helmet');
const morgan = require('morgan');
const apiRouter = require('./src/routes/api');

const app = express();
app.use(helmet());
app.use(morgan('dev'));
app.use(express.json());

app.use('/api', apiRouter);

const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Server running on ${port}`));
module.exports = app;
