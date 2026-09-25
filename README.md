# Arcade Control Center

A Java application that demonstrates the Bridge and Adapter design patterns.

## Technologies

- Java
- HTML
- CSS
- JavaScript

## Bridge Pattern

The Bridge pattern separates controllers from game devices.

Main classes:

- GameDevice
- ArcadeMachine
- RacingMachine
- Controller
- AdvancedController

## Adapter Pattern

The Adapter pattern allows the legacy arcade machine to work with the new system.

Main classes:

- LegacyArcadeMachine
- LegacyArcadeAdapter

## How to Run

Open the terminal in the project folder.

Compile:

```bash
javac -d out src\bridge\*.java src\adapter\*.java src\Main.java
