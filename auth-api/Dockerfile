FROM node:16

WORKDIR .
COPY package*.json ./

RUN yarn
COPY . .
EXPOSE 8001
CMD ["node", "app.js"]


