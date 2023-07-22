# Simple Console Games 🎮

This is a super fun console-based collection of games that I built using Java 17 and Gradle 7.3. The project is a bit of an adventure for me as I'm exploring how to create engaging games mainly using the standard libraries in Java.

## What's up with Simple Console Games?

As someone who is still in the early stages of my programming journey, I wanted to really understand the core of Java and its standard libraries. So, I thought, why not learn by doing something fun - hence, Simple Games was born!

The games here, like "Rock, Paper, Scissors" (Janken) and "Hit and Blow", are simple, but they've helped me understand different programming concepts.

## Project Structure

This project uses the following technologies:

- Java 17
- Gradle 7.3
- Reflections for classpath scanning
- Lombok for reducing boilerplate code

The application logic is divided into multiple services, models, enums, and utilities to maintain a clean architecture.

```
./
├── README.md
├── app
│   ├── build.gradle
│   └── src
│       └── main
│           └── java
│               └── simple
│                   └── games
│                       ├── App.java
│                       ├── annotations
│                       │   └── GameImpl.java
│                       ├── enums
│                       │   ├── Hand.java
│                       │   └── YesNoResponse.java
│                       ├── models
│                       │   └── User.java
│                       ├── services
│                       │   ├── AbstractGame.java
│                       │   ├── Game.java
│                       │   ├── GameManager.java
│                       │   ├── HitAndBlow.java
│                       │   └── JankenGame.java
│                       └── utils
│                           ├── CalculateUtil.java
│                           ├── ConsoleUtil.java
│                           └── ValidatorUtil.java
├── clean-in-wsl.sh <------------------------------------------------------ to clean enviroment in wsl
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew <------------------------------------------------------ I think Gradle can still run this.
├── gradlew.bat
├── settings.gradle
├── setup-in-wsl.sh <------------------------------------------------------ to setup enviroment in wsl (java, gradle ...)
└── shared.sh
```

## How to Run

To run this application, make sure you have Java 17 and Gradle 7.3 installed on your machine. Then, you can run the application by executing the following command in your terminal:

```
    ./setup-in-wsl.sh

    gradle clean build

    gradle run
```
