FROM node:16

WORKDIR .
COPY package*.json ./
COPY tsconfig*.json ./

RUN yarn

COPY . .

EXPOSE 8081

CMD yarn install && yarn startDev




