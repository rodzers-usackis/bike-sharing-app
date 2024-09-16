# Use the official Node.js image as the base image
FROM node:16

# Set the working directory
WORKDIR /app

# Copy the package.json and package-lock.json files
COPY package*.json ./

# Force install the dependencies
RUN npm install --force

# Copy the rest of the application files
COPY . .

# Expose the port that the application will run on
EXPOSE 3000

# Specify the command to run when the container starts
CMD ["npm", "start"]