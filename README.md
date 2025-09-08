# BookNest

## Description
This project is built with Clean Architecture, MVVM and Koin for dependency injection. Two screens have been implemented, a screen that shows a list of books and a details screen showing specific information about a book.
A caching mechanism is including with the use of Room, saving the list of books that gets loaded first (if any) when the app starts and then continue with the api call. In the details screen we also check first
if the id of the book already exists  in the database, if not an api call books/{id} takes place. Test files have also be included for both view models and for the Repository.

The project is fully modularized, consisting of:

+ :app module (Ui, View Models, DI, Navigation, MainApp) 
+ :common-resources module (drawables, colors, strings) 
+ :data module (Database operations, Repository implementation, Models, Mappers, Api)
+ :domain module (Repository, Models, UseCases)

## Features
+ Clean Architecture
+ MVVM
+ Modularization (app, common-resources, domain, data)
+ Koin for DI
+ Flow, Coroutines
+ Compose
+ Room
+ Retrofit
+ JUnit



