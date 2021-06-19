# cst438-group-4-package-booking
Package booking microservice for cst438 final project.
## Overview
In this project we created a website that allows to book vacation packages. The user is able to search for available packages by filling out a simple form with thei trips search criteria. The results are then displayed to user user on cards with all relevant package information clearly displayed. To create a booking the user must be registered on the site and signed in. If the user is not signed in they will be prompted to sign in and given the option to register.
### Test Search Criteria
Departure City: City1  
Destination City: Los Vegas  
Departure Date: 07/02/2021  
Return Date: 07/17/2021  
Travelers: 2

## Microservices
### Car Service
For the car service we use agreed upon "Standard" car type, which is coded into the our car service. When booking we do a GET request and send location, pick-up date, and return date. If a vechile is available we receive a 200 response with reservation details. We can also cancel a reservation by sending the reservation id as a path variable in a DELETE request.
### Flight Service
For the Flight service we also established specific test data for the flight. Flight reservations are made using a POST request to the flight service REST API with flight id, number of passengers, and user email. Cancellations are made by sending the reservation id as a path variable in a DELETE request.
### Hotel Service
For the hotel service we do a GET request and are returned available rooms for the given location, check-in date, and check-out date. We then create a booking in our system and this information is mapped to the booking class provided by the hotel service and sent to the hotel service using a POST request. Cancellations can be made by sending the booking id as a path variable in a DELETE request to the hotel service.
## Database Design

## 
