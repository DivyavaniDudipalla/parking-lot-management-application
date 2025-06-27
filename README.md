Below are the steps to run the application

1. Go to folder **parking-lot-management-application\target** then download the **parking-lot-management-application-0.0.1-SNAPSHOT.jar**
2. Run the application by using command **java -jar parking-lot-management-application-0.0.1-SNAPSHOT.jar**
   Note: Supported java version java 17
3. In the Spring Shell console, use the following commands:
   - create-parking-lot 10 8 5 - Create a parking lot with 10 small, 8 large, and 5 oversize slots
   - park ABC123 SMALL_CAR - Park a small car with license plate ABC123
   - park XYZ789 SUV - Park an SUV with license plate XYZ789
   - status - Show the current status of the parking lot
   - find ABC123 - Find where vehicle ABC123 is parked
   - available - Show all available slots
   - available SMALL - Show available small slots
   - leave ABC123 - Remove vehicle ABC123 from the parking lot

