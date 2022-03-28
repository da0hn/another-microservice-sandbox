import express from "express";

const app = express();
const env = process.env;
const PORT = env.PORT || 8001;

app.get('/api/authentication/status', (request, response) => {
  return response.json({
    service: 'auth-api',
    status: 'up',
    httpStatus: 200
  })
})

app.listen(PORT, () => {
  console.info(`Server started successfully at port ${PORT}`)
})

