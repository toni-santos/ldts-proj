# **LDTS_805 - Out of the Breach**

![temp](/others/outofthebreach1.jpg)
---

Out of the Breach is a Java based video game that draws inspiration from the turn-based indie title [Into the Breach](https://subsetgames.com/itb.html) from Subset Games.

In Out of The Breach you take the role of the aliens from Into the Breach, while keeping the same core mechanics from the original game, such as defending infrastructures and using the environment as your weapon.

This project is part of the LDTS (2020/21) unit of the Bachelor of Informatics and Computing Engineering course [@FEUP](https://github.com/FEUP) and its goal is applying the ideal design patterns and philosophies to the code that is written. The theme of the project was chosen by the group formed by [António Santos (202008004)](https://github.com/toni-santos), [João Pereira (202007145)](https://github.com/ttoino) and [Pedro Silva (202004985)](https://github.com/pedrosilva17).

### Implemented  Features
> Mouse controls: Just like in Into the Breach the main control scheme follows a point and click system.

> Enemy types: Enemies can have several abilities and have an archetype ("Cannon", "Tank", "Flying").

> Terrain types: Different terrain types have different effects on the strategy of the player.

### Planned Features
> Environmental elements: Abilities will push entities and they will behave accordingly, per example, pushing an enemy against a mountain deals damage to the mountain and the enemy being pushed.

> Extra controls: The game can be played with keyboard only! (not really recommended however).

> Responsive enemies: Enemy intelligence will have different behaviour according to their archetypes.

### Extended Features

We are unsure if these features will make the cut due to time constraints, since they might not introduce any valuable insight into the topics being evaluated.

> Random map generation: Randomly generate a new map with new enemies each new game!

### Design

Going into this project the general design philosophy adopted was MVC, since it lends itself well to the specific type of game we are making, it being a turn-based game with few real-time events happening.

We will therefore be handling several issues concerning this particular design philosophy, especially in the development of the controllers.


⚠ [IN PROGRESS] ⚠