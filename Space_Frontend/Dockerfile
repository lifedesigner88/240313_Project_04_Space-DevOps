# Starting from Node 20 base image
FROM node:20

# Setting the working directory
WORKDIR /app

# Copying package.json and package-lock.json first
COPY package*.json ./

# Install dependencies
RUN npm install

# Copying the rest of the code
COPY . .

# Default command to run on container start
ENTRYPOINT [ "npm", "run", "dev" ]
