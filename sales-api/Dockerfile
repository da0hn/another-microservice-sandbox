FROM node:16

WORKDIR .
COPY package*.json ./

RUN yarn
COPY . .
EXPOSE 8002
CMD ["node", "app.js"]


