# Integration 5 - Bike sharing

## Packages
We have two backend packages for now.  
The first one is real_backend. This is the local application. This is also the most up to date.  
The second one is spring_backend. This is the application that is deployed on google app engine.

## Frontend
Frontend consists of 7 pages.  
- LandingMain
- About Us
- Map
  - Stations
- How To
- Dashboard
- Profile

Most of the pages are only there for the purpose of navigability based on wireframes, so they're currently content-less.  

In order to display some additional data, like for example the station whenever you click on a marker on map, I added a jsonServer with dummy data.  

This will later be swapped with real data of course.

Predictions page, might sometimes be stuck in a permanent loading phase, this is due to the python server.  

If that happens, please refresh the page.  

In order to run the frontend.
```
npm run dev
```

## API Endpoints
We currently have 2 API endpoints.

### Starting a ride
You can send a POST request to ```/api/bike/ride/start``` to start a ride

### Creating a bike
To see the application interact with the database you can also send a post request to ```/api/bike/create/{actCost}/{pricePerMin}```

## Google Cloud Database
To run the application with the database locally, you have to identify yourself.

To do this, first set your project to this project with  
```google config set project project_name```  
(In our case ```gcloud config set project kdg-acs3-integration-5-team-2```)  
Then use the following command  
```google auth application-default login```  
This creates a credentials.json file that the application will use to authenticate you with the google cloud database.