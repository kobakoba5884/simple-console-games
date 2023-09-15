# Simple Console Games 🎮

This is a simple console-based collection of games that I built using Java 17 and Gradle 7.3.
I tried creating this as part of my Java studies. 
Honestly, there's no need to make it a public repository, but I guess it's for keepsake.

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
