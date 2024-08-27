# Exercise 01: Car Park Simulation
A school assignment in which we have to simulate a multi-storey car park. We have to use java and design suitable UML diagrams for it.

## The Task
> **You are developing a car park simulation. This supports the following functionalities:**
> - The entrance barrier opens when you have taken a ticket. You only get a ticket if there are still free spaces available.
> - Pay the parking price (price per minute) at one of the pay stations on the floors.
> - Exit the car park at one of the two exit barriers. The barrier will only open if the ticket has been paid for.
> - Show on the display in front of the car park how many spaces are still free.
>
> **1. develop this simulation as a console application in Java in a git repository.**
>
> **2. draw the necessary/meaningful UML diagrams.**

*&copy; 2024 Felix Stadelmann, Therwil (Translated using DeepL)*

## Build / Run the Project
You can easily build and/or run the project using the included gradle wrapper.
### Run the Project directly
```shell
./gradlew run  # For Linux or macOS
gradlew.bat run  # For Windows
```

### Build the Project
*This also runs the tests automatically*
```shell
./gradlew build  # For Linux or macOS
gradlew.bat build  # For Windows
```
The built jar can be found under `app/build/libs/car-parc-sim.jar`

## UML Class Diagram
![class diagram](https://github.com/user-attachments/assets/fdd04bfd-b462-4ca3-b6d8-9eb5830c3587)
