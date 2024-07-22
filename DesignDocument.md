# Board Game Arena Planner Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram 

Place your class diagrams below. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)
```mermaid
---
title: BGArena Planner Design
---
classDiagram

    class BoardGame {
        +String name
        +int id
        +int minPlayers
        +int maxPlayers
        +int minPlayTime
        +int maxPlayTime
        +double difficulty
        +int rank
        +double rating
        +int yearPublished
        +boolean isMatched
        +getName(): String
        +getId(): int
        +getMinPlayers(): int
        +getMaxPlayers(): int
        +getMinPlayTime(): int
        +getMaxPlayTime(): int
        +getDifficulty(): double
        +getRank(): int
        +getRating(): double
        +getYearPublished(): int
        +setMatched(boolean): void
    }
    class GameData {
        <<enum>>
        +NAME
        +ID
        +MIN_PLAYERS
        +MAX_PLAYERS
        +MIN_TIME
        +MAX_TIME
        +DIFFICULTY
        +RANK
        +RATING
        +YEAR
        +String getColumnName(): String
        +static GameData fromColumnName(String columnName): GameData
        +static GameData fromString(String columnName): GameData
    }
    class IPlanner {
        <<interface>>
        +Stream<BoardGame> filter(String filter): Stream<BoardGame>
        Stream<BoardGame> filter(String filter, GameData sortOn): Stream<BoardGame>
        Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending): Stream<BoardGame>
    }
    class IGameList {
        <<interface>>
        +List<String> getGameNames(): List<String>
        +void clear(): void
        +int count(): int
        +void saveGame(String filename): void
        +void addToList(String str, Stream<BoardGame> filtered): void
        +void removeFromList(String str): void
    }
    class Planner {
        -Set<BoardGame> games
        +Stream<BoardGame> filter(String filter): Stream<BoardGame>
        +Stream<BoardGame> filter(String filter, GameData sortOn): Stream<BoardGame>
        +Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending): Stream<BoardGame>
        +void reset(): void
    }
    class GameLoader {
        +static Set<BoardGame> loadGamesFile(String filename): Set<BoardGame>
    }
    class GameList {
        -List<BoardGame>games
        +List<String> getGameNames(): List<String>
        +void clear(): void
        +int count(): int
        +void saveGame(String filename): void
        +void addToList(String str, Stream<BoardGame> filtered): void
        +void removeFromList(String str): void
    }
    %% Relationships
    GameList --> IGameList
    Planner --> IPlanner
    GameList o-- BoardGame
    GamesLoader ..> BoardGame
    Planner ..> BoardGame
    GameData ..> BoardGame
    IGameList <|-- GameList
    IPlanner <|--Planner
```

### Provided Code

Provide a class diagram for the provide code as you read through it.  For the classes you are adding, you will create them as a separate diagram, so for now, you can just point towards the interfaces for the provided code diagram.



### Your Plans/Design

Create a class diagram for the classes you plan to create. This is your initial design, and it is okay if it changes. Your starting points are the interfaces. 
```mermaid

    classDiagram

        class BoardGame {
        +String name
        +int id
        +int minPlayers
        +int maxPlayers
        +int minPlayTime
        +int maxPlayTime
        +double difficulty
        +int rank
        +double rating
        +int yearPublished
        +boolean isMatched
        +String getName(): String
        +int getId(): int
        +int getMinPlayers(): int
        +int getMaxPlayers(): int
        +int getMinPlayTime(): int
        +int getMaxPlayTime(): int
        +double getDifficulty(): double
        +int getRank(): int
        +double getRating(): double
        +int getYearPublished(): int
    }
    class IGameList {
        <<interface>>
        +List<String> getGameNames(): List<String>
        +void clear(): void
        +int count(): int
        +void saveGame(String filename): void
        +void addToList(String str, Stream<BoardGame> filtered): void
        +void removeFromList(String str): void
    }

    class IPlanner {
        <<interface>>
        +Stream<BoardGame> filter(String filter): Stream<BoardGame>
        +Stream<BoardGame> filter(String filter, GameData sortOn): Stream<BoardGame>
        +Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending): Stream<BoardGame>
        +void reset(): void
    }
    class GameList {
        -List<BoardGame> games
        +List<String> getGameNames(): List<String>
        +void clear(): void
        +int count(): int
        +void saveGame(String filename): void
        +void addToList(String str, Stream<BoardGame> filtered): void
        +void removeFromList(String str): void
    }
    class Planner {
        -Set<BoardGame> games
        +Stream<BoardGame> filter(String filter): Stream<BoardGame>
        +Stream<BoardGame> filter(String filter, GameData sortOn): Stream<BoardGame>
        +Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending): Stream<BoardGame>
        +void reset(): void
    }
    

```

## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test create a new BoardGame
2. Test GameList to get the name of the game
3. Test clear the gaming list
4. Test the filter with sort




## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the fil in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

For the final design, you just need to do a single diagram that includes both the original classes and the classes you added. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.





## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning to information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 


Initially, I focused on creating basic interfaces and classes such as IGameList and IPlanner to manage board games and user interactions. However, as the project progressed, I realized that the filtering system in the Planner class needed to be more flexible and detailed. Originally, it was designed quite simply, but I quickly understood that users would prefer a system with more specific filtering options. This prompted me to implement a more complex filtering system that could handle different comparison operators and allow multiple filtering conditions to be used simultaneously. This change was crucial for enhancing the practicality and user-friendliness of the system.

Another significant adjustment was the enhancement of the GameList class's capabilities, supporting the saving and loading of game lists. Initially, I did not fully consider how useful it would be for users to be able to save their game collections and load them later. This additional feature made the system more practical and better suited to actual needs. The most challenging part of this process was ensuring the correctness of the filtering and sorting functions. Ensuring that these functions could work correctly with various attributes and sorting options required careful consideration and testing. Balancing the needs for a powerful yet easy-to-use system was a difficult task, but overcoming these challenges ultimately led to a more refined and robust design.