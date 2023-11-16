FROM node:18-alpine AS build

WORKDIR /frontend/HereHear

COPY package.json .

RUN npm cache clean --force

# Typescript 먼저 install
RUN npm install -g typescript vite

RUN npm install @vitejs/plugin-react-swc

RUN npm install

ENV NODE_ENV=production

COPY . .

RUN npm run build

FROM nginx:1.21.3-alpine

COPY nginx.conf /etc/nginx/nginx.conf

COPY --from=build /frontend/HereHear/dist /usr/share/nginx/html

EXPOSE 3000

CMD [ "nginx", "-g", "daemon off;" ]
