# **LDTS_805 - Out of the Breach**

![main](/others/outofthebreach1.jpg)
---

Out of the Breach is a Java based video game that draws inspiration from the turn-based indie title [Into the Breach](https://subsetgames.com/itb.html) from Subset Games.

In Out of The Breach you take the role of the aliens from Into the Breach, while keeping the same core mechanics from the original game, such as defending infrastructures and using the environment as your weapon.

This project is part of the LDTS (2020/21) unit of the Bachelor of Informatics and Computing Engineering course [@FEUP](https://github.com/FEUP) and its goal is applying the ideal design patterns and philosophies to the code that is written. The theme of the project was chosen by the group formed by [António Santos (202008004)](https://github.com/toni-santos), [João Pereira (202007145)](https://github.com/ttoino) and [Pedro Silva (202004985)](https://github.com/pedrosilva17).

---
### Implemented  Features

##### This is a dramatized recreation, as we are working with lanterna and are limited by its features, or lack thereof.

![game](/others/game_vision.jpg)

> Enemy types: Enemies can have several abilities and have an archetype ("Cannon", "Tank", "Flying").

> Terrain types: Different terrain types have different effects on the strategy of the player.

### Planned Features

> Menus: Add a main menu screen.

##### This is a dramatized recreation, as we are working with lanterna and are limited by its features, or lack thereof, in this case the background image will most likely be some sort of an ASCII art.
![main menu](/others/outofthebreach_menu_mock.jpg)

> Mouse controls: Just like in Into the Breach the main control scheme follows a point and click system.

> Environmental elements: Abilities will push entities and they will behave accordingly, per example, pushing an enemy against a mountain deals damage to the mountain and the enemy being pushed.

> Extra controls: The game can be played with keyboard only! (not really recommended however).

> Responsive enemies: Enemy intelligence will have different behaviour according to their archetypes.

### Extended Features

We are unsure if these features will make the cut due to time constraints, since they might not introduce any valuable insight into the topics being evaluated.

> Random map generation: Randomly generate a new map with new enemies each new game!

---
### Design

Going into this project the general design philosophy adopted was MVC, since it lends itself well to the specific type of game we are making, it being a turn-based game with few real-time events happening.

![MVC](/others/mvc.png)

We will therefore be handling several issues concerning this particular design philosophy, especially in the development of the controllers. Either way, we have already at this stage faced a few problems, mainly concerning creational patterns.

### Creating Creatures and Terrain

- Problem in context

    Creating creatures (aka entities) and terrains was a challenge due to the nature of these concepts. Both of these problems are similar in essence due to the fact that in both cases there are several types of both with, effectively, the same properties. That means, a "flying robot" and a "canon alien" are in all the same but they belong to different factions (robots and aliens) and different unit types (flying and canon), the only real differences will be concerning their abilities and other execution rules (p.e. player control).

- The Pattern

    When approaching these similar issues we came up with 2 strategies, implementing one of them in the creation of creatures and the other in the creation of terrains.

    When dealing with terrains, we simply create a new class for each type of terrain. 

    When dealing with creatures, we decided to create a single class "Creature" and delegate the logic for this problem to be implemented by some other piece of code in the controller "realm". 

    These solutions do not fit the description of any design patterns that we are aware of.

- Implementation

    [Terrains](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0805/tree/main/src/main/java/model/game/board)

    [Creatures](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0805/blob/main/src/main/java/model/game/entity/Creature.java)

- Consequences
    
    For terrains, although this is an easy strategy to implement, its' scalability is limited and it is most certainly not an elegant solution.

    For creatures, this solution saves information about the type and faction of the creature which we feel it is awkward to do, but may help us in the future.


### Creating abilities

- Problem in context

    The creation of abilities was a feature that we feared could easily spiral out of control, hence the necessity to write code, from the start, that is not only flexible but also easy to understand. 

- The Pattern

    The solution was to create an enum class, a property of Java that came in handy due to the fact that all abilites share the same attributes and there is no need to actually make them distinct. 

    Like the previous issue, we do not know any design patterns that fit the description of our solution.

- Implementation

    [Abilities](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0805/blob/main/src/main/java/model/game/entity/Ability.java)

- Consequences
    
    We find this solution extremely simple and intuitive, as it replicates what some games do, creating a table of abilities. This allows us to keep everything neatly organized and compiled in a single file. This fact not only makes it easy to create new abilites but also makes the code that implements these abilities more readable.

---

### Code Smells

- #### Terrains

    As previously stated we are aware that the current implementation of the terrains is limiting and hard to scale, however we currently want to see how this decision is going to influence the rest of the code that is built. In any case the solution that we have stumbled upon to fix this issue is to build this feature as a Factory, according to the Factory design pattern.

- #### Creatures
    
    While we are aware that saving information about the type and faction of the creature is not ideal and could be considered a code smell, we find this solution to be not only elegant, but also helpful to keep ourselves tied to the MVC philosophy.