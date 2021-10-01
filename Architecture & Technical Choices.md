# Architecture & Technical Choices

This document explain some of the architectural decisions we've made for the Digital Menu Application. 


## Why multiple applications?

For our project we have chosen to create multiple applications. One for the customer on a mobile device and two for the staff of the restaurant on a desktop. This is an easy way to split the responsibilities of each app without spending too much time on roles and security. 
  
Customers only have the options to order dishes and get some info about them. The staff of the restaurant don’t need to order anything. They only have to see the orders, manage their inventory and adjust the menu. 
  

## Why Microservices?

Microservices should make it easy to scale up the application and add more features when necessary. It also allows us to host the app on different servers. Although the simplicity of a monolithic application makes it an attractive options we're going to use microservices with the future in mind. 

## Front-end

For our front-end we chose to use React as a JavaScript framework’. A JavaScript framework allows for faster workflow, because you can divide your SPA in components. Components are reusable and independent parts of code. Because the page is built up from multiple components, you can render only the parts that you need.

React is one of the most used front-end frameworks and is supported by Facebook. It has a gentle learning curve compared to Angular, which is also a popular front-end framework supported by Google. For professional use it is one of the most common JavaScript frameworks, so hiring people who have experience with it should be no problem.
It also has really good backwards compatibility and works well in combination with a lot of API’s.

  

## Back-end

We chose to use Java for our back-end. The majority of our group wanted to learn a new OOP language. Java is a language based on the principle of “Write once, runs anywhere”. Because Java is an object-oriented compiled language you can run it on any platform.   

There is also a large number of Java libraries that can be used for multitude of things such unit testing and parsing. Using libraries significantly decreases the time needed to set up a back-end of web apps. Most of them are open-source and supported by Java developers.

 Java is also highly scalable due to the automatic memory management and garbage collection. 
  

## SQL or NoSQL?

For the database there are multiple options to choose between. The biggest choice we have to make is to choose if we want to use SQL or NoSQL. The biggest difference between those two options is the way that the data will be stored. SQL stores all the data in tables with relations between them. NoSQL stores all data in a document (JSON-Format). The data does not all have to be in the same format. 

We have chosen to use SQL because the relationship between tables is sufficient and we have more experience with it as a group. 
  
## ORM

The most important reason to use Object Oriented Mapping is so that you can have a rich object oriented business model and still be able to store it and write queries faster to a relational database.

 There are a good amount of DAL templates that can almost achieve the same thing that an ORM can, but an ORM can do this automatically and in most cases faster. The upside of using SQL is that you can highly customize the queries you use. The ORM often includes queries that perform better than the ones you write and it comes with a lot of advanced features out of the box.

  
Another big upside of an ORM is also that you can write it in the language you’re already using.