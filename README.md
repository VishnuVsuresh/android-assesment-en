# Cat Knowledge Base

## Instructions
This test should not take more than 2 hours (even with additional tasks).

Don't fork this repository or make a pool request. 

## Basic task

The app allows users to learn new facts about cats. Each fact on a screen looks like a picture with a cat, under which there is text information, as well as the date when the fact was added. All facts are placed in a vertical scrollable feed.

* Initially, the user is shown a blank screen with an invitation to learn a new fact. 
* When you click on the button in the action bar, one random fact is loaded, as well as a random picture for this fact.
  - A random cat image is taken from the service https://cataas.com
  - A random fact is taken from https://alexwohlbruck.github.io/cat-facts/
* The next time user clicks, a new fact and image are loaded and added to the feed.

### Mandatory requirements
- The code is written only in Kotlin. You can't use Java/Scala.
- MVP / MVVM architecture.
- Use of Dependency Injection.

### Extra requirements
- The app is designed in Material Design.
- Unit tests.

## Additional task 1
Implement the offline mode of the app. All uploaded facts are saved to the database (preferably using Room) and displayed from the database when there is no internet connection.

## Additional task 2
Implement the ability to delete a fact from the screen and from the database. You can do this through a long press on the corresponding fact.
