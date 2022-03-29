import express from 'express';

const app = express();

const env = process.env;
const PORT = env.PORT || 8002;

app.listen(PORT, () => {
  console.info(`Server started successfully at port ${PORT}`);
});





