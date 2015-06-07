# rabbit-todo
A simple collaborative real time application with Angular and Spring MVC. The purpose of this application is to showcase the integration between Angular / Spring MVC using MongoDB as backing store and websockets for the interactivity.

![alt tag](https://raw.github.com/bhnedo/rabbit-todo/rabbit-todo.png)

### Requirements

*   JDK 8
*   MongoDB
*   Gradle
*   Bower
*   Compass (optional)

### Setup
Install the required dependencies. Clone the repository. Go to the main directory and run `bower install` to setup JS dependencies. Next, run `gradle assemble` to configure the jar artifacts. Run the embedded Jetty container with `gradle appRun` and go to `http://localhost:8080/rabbit-todo`.
