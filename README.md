
# Falling Words

This project implements a trivia game application using the Clean MVVM architecture pattern with Jetpack Compose in Kotlin. The application allows users to play trivia games, save their game progress, and view statistics from previous game sessions. It leverages various Android Jetpack libraries, Hilt for dependency injection, Retrofit for networking, and Jetpack Compose for building the user interface.

<p float="left">
  <img src="https://github.com/arashalif/FallingWord/assets/52599495/797e8821-62c4-4ad6-85eb-17d89d8c6138" width="200" height="400" />
  <img src="https://github.com/arashalif/FallingWord/assets/52599495/1de05261-f293-45b5-9457-b1650f91768e" width="200" height="400" /> 
  <img src="https://github.com/arashalif/FallingWord/assets/52599495/f16355a8-ada8-47b0-b0b8-371bf74d55f9" width="200" height="400" />
  <img src="https://github.com/arashalif/FallingWord/assets/52599495/b675307d-6f55-4b16-9e1f-f7afc13fc5fc" width="200" height="400" />
</p>

## Features

- Save Progress Game: Allows users to save their progress in the game so they can resume later from where they left off.
- Total Stats: Provides users with comprehensive statistics about their overall performance, including total games played, total questions answered, total correct answers, and total score.
- Dynamic Score on Game Screen: Displays a dynamic score on the game screen that changes based on the time taken to answer questions or other relevant factors, providing an engaging and interactive experience.
- Animation of Falling Word: Enhances the user experience with an animation of falling words during gameplay, adding visual appeal and making the game more immersive and enjoyable.





## Time Spent [![wakatime](https://wakatime.com/badge/user/b0875c57-3929-42f4-ac0a-9407f2d21f5f/project/018dc478-e3f0-4450-b46d-1551dfcc13fb.svg)](https://wakatime.com/badge/user/b0875c57-3929-42f4-ac0a-9407f2d21f5f/project/018dc478-e3f0-4450-b46d-1551dfcc13fb)

Approximately 6:15 hours were spent on this project:

- 45 minutes thinking about designing Domain of app and selecting features
- 3 hours for implementing domain and data layers.
- 2.5 hours for implementing UI components.

Top 10 classes I spent most time:
- 35 mins	…iousGameStatsUseCaseTest.kt 
- 17 mins	…se/SaveTotalStatsUseCase.kt 
- 16 mins	app/build.gradle.kts 
- 14 mins	…e/InitialGameUseCaseTest.kt 
- 13 mins	…/local/GameDataStoreImpl.kt 
- 11 mins	https://readme.so 
- 9 mins	…PreviousGameStatsUseCase.kt 
- 8 mins	…a/GameRepositoryImplTest.kt 
- 7 mins	…/data/GameRepositoryImpl.kt 
- 6 mins	…mote/WordsDataSourceImpl.kt 

## Why I Solved The Problem In This Particular Way

### Repository Design Pattern and Separating Domain Layer

In this project, I chose to implement the repository design pattern and separate the domain layer for several reasons:

1. **Separation of Concerns**: By separating concerns into different layers, such as the data, domain, and presentation layers, the codebase becomes more modular and easier to maintain. Each layer has its own responsibilities and can be developed, tested, and modified independently.

2. **Abstraction of Data Sources**: The repository acts as an abstraction layer that shields the domain layer from the details of data retrieval and storage. It provides a clean API for accessing data, regardless of the underlying data sources, such as local databases, network services, or in-memory caches.

3. **Decoupling from Implementation Details**: With the repository pattern, the domain layer depends only on abstractions defined by interfaces, rather than concrete implementations of data sources. This decoupling allows for easier substitution of data sources and facilitates testing by enabling the use of mock objects.

4. **Promotion of Testability**: Separating the domain layer and using the repository pattern promotes testability. The business logic encapsulated in the use cases can be unit-tested independently of the data sources, leading to more reliable and maintainable tests.

5. **Flexibility and Scalability**: Adopting the repository pattern provides flexibility and scalability to the application. New data sources can be added or existing ones replaced without modifying the domain layer, as long as they adhere to the repository interfaces. This makes it easier to adapt the application to changing requirements or integrate with different backend systems.

### Writing Use Cases

Use cases encapsulate the business logic of the application and define the interactions between the presentation and domain layers. Here's why I decided to write use cases:

1. **Clear Separation of Concerns**: Use cases define the actions that can be performed in the application's domain, abstracting away the details of how data is fetched, processed, or manipulated. This separation ensures that the business logic remains focused and isolated from the presentation layer.

2. **Improved Readability and Maintainability**: By encapsulating business logic in use cases, the codebase becomes more readable and maintainable. Use cases serve as entry points to the domain layer, making it easier for developers to understand and reason about the application's behavior.

3. **Single Responsibility Principle (SRP)**: Each use case typically represents a single, well-defined task or scenario in the application. Following the SRP ensures that use cases are cohesive and focused on specific responsibilities, which contributes to cleaner and more maintainable code.

4. **Testability**: Use cases are inherently testable units of code. They can be unit-tested in isolation from the rest of the application, allowing for thorough validation of the business logic and behavior under different conditions.

5. **Flexibility and Reusability**: Use cases can be reused across different parts of the application or even in entirely different projects. This promotes code reuse and reduces duplication, leading to a more efficient development process.

Overall, writing use cases facilitates the implementation of clean architecture principles, promotes separation of concerns, and enhances the maintainability and testability of the application.
##  What I Would Do Differently If I Had More Time.

With more time, the following additional features could be implemented:

- Save more games: Implement functionality to save at least 3 previous game sessions using a non-SQL database for persistence.
- Write edge case scenarios: Add additional unit tests to cover edge cases and corner scenarios thoroughly.
- Enhance UI: Improve the UI with additional animations, better layout design, and more polished visual elements and writing UI test.
## Tools Used
- Jetpack Compose: A modern toolkit for building native Android UI.
- AndroidX Core KTX: Kotlin extensions for core Android libraries.
- AndroidX Lifecycle: Extensions for ViewModel and LiveData.
- Hilt: Dependency injection library for Android.
- Retrofit: A type-safe HTTP client for Android and Java.
- OkHttp: An HTTP client for Android and Java applications.
- DataStore: A modern data storage solution for Android.
- Navigation Component: A library for handling navigation between destinations.
- JUnit: A unit testing framework for Java.
- Mockito: A mocking framework for unit tests in Java.
- Coroutines: A library for asynchronous programming in Kotlin.
- Kotlinx Serialization: Library for JSON serialization and deserialization.
- AndroidX Core Testing: Library for testing LiveData and other architecture components.
- Jetpack Compose Testing: Libraries for testing Jetpack Compose UI.
- Espresso: A testing framework for UI tests in Android.
- AndroidX Test Ext: Extensions for writing Android tests.
