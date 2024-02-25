# MAD - Exercise 01
## Tasks
* Watch the Kotlin Crashcourse Video in Moodle or complete the tutorials **[Introduction to programming in Kotlin](https://developer.android.com/courses/pathways/android-basics-compose-unit-1-pathway-1)** and **[Kotlin fundamentals](https://developer.android.com/courses/pathways/android-basics-compose-unit-2-pathway-1
)**.
* Answer the questions inside this Readme.md file and push it to your repository.
* Submit your coding solution of the Number Guessing Game inside the repository.
* Submit the link to your repository in Moodle.

## Questions
### Describe how Kotlin handles null safety. What are nullable types and non-null types in Kotlin? (0,5 points)

#### Null Safety in Kotlin
Kotlin handles null safety through its system of nullable and non-null types, ensuring that null pointer exceptions, common in many programming languages, are significantly reduced. In Kotlin, a type is non-null by default, meaning it cannot hold a null value. If you want to allow a variable to be null, you must explicitly declare it as nullable by adding a _?_ after the type.

##### Non-null types 
- are those that cannot hold a null value. 
- Attempting to assign null to such types will result in a compile-time error. 
- This approach provides a strong guarantee against null pointer exceptions.

##### Nullable types 
- are declared by appending ? to the type. 
- Variables of nullable types can hold a null value. 
- When working with variables of nullable types, Kotlin requires explicit handling of the possibility of null, either by checking for null before usage or by using safe calls (?.) and the Elvis operator (?:).


```kotlin 
// example code snippet
val nonNullableString: String = "I cannot be null"
// nonNullableString = null // This would result in a compile-time error

val nullableString: String? = "I can be null"
// This is fine. nullableString can be set to null
val anotherNullableString: String? = null

// Safe call example
val length = nullableString?.length // This will be null if nullableString is null

// Elvis operator example
val lengthOrZero = nullableString?.length ?: 0 // This will be 0 if nullableString is null

```

### What are lambda expressions and higher order functions in Kotlin? Why would you store a function inside a variable? (0,5 points)

- ##### Lambda expressions:
    are essentially anonymous functions that can be treated as values: 
you can assign them to variables, pass them as arguments, or return them from functions. 
They are very useful for creating concise and expressive code, especially when working with collections
or any context where a short function is needed temporarily.

- ##### Higher-order functions: 
    are functions that take functions as parameters and/or return functions. This capability allows for powerful abstractions and code reuse.

You might store a function in a variable for several reasons, such as: 
- to pass it as an argument to another function.
- to delay its execution. 
- to dynamically decide which function to call.

```kotlin 
// A simple lambda expression assigned to a variable
val sum: (Int, Int) -> Int = { a, b -> a + b }

// A higher-order function that takes a function as a parameter
fun calculate(operation: (Int, Int) -> Int, a: Int, b: Int): Int {
return operation(a, b)
}

// Using the lambda expression
val result = calculate(sum, 5, 3) // This will give 8
```
### Provide a solution for the following number guessing game inside `App.kt`. (3 points)

## Number Guessing Game in Kotlin
The game is a simple number guessing game. The task is to generate a random, max 9-digit, number. The number must **not contain repeating digits**. Valid digits are 1-9.
Ask the user to guess the max 9-digit number. The game is finished when the user guesses the correct digits in the correct order.
In each round, the user gets feedback about the number of correct digits and the number of correct digits in the correct position.
The output should be in the format "n:m", where "n" is the number of digits guessed correctly regardless of their position, 
and "m" is the number of digits guessed correctly at their correct position. Here are some examples:

This example shows the game flow with 4-digits to guess (the default argument)

Generated number: 8576
-	User input: 1234, Output: 0:0
-	User input: 5678, Output: 4:1
-	User input: 5555, Output: 1:1
-	User input: 3586, Output: 3:2
-	User input: 8576, Output: 4:4 -> user wins

Take a look into the provided code structure in `src/main/kotlin/App.kt`. Implement the following methods (lambda expressions):
- _playNumberGame(digitsToGuess: Int = 4)_ (1 point): The main game loop that handles user input and game state. Make use of _generateRandomNonRepeatingNumber_ and _checkUserInputAgainstGeneratedNumber_ here. This function also utilizes a default argument 
- _generateRandomNonRepeatingNumber_ (1 point): A lambda expression that generates a random number with non-repeating digits of a specified length.
- _checkUserInputAgainstGeneratedNumber_ (1 point): A lambda expression that compares the user's input against the generated number and provides feedback.

``CompareResult.kt`` This class is a data structure which helps with bundling the result of the comparison of the user input and the generated number. Look at the toSting() and use it in your output.

Run the project with `./gradlew run` and test your implementation with the provided tests in `src/test/kotlin/AppTest.kt` with `./gradlew test`.

# Project Structure
The project is structured into two main Kotlin files:

**App.kt**: Contains the main game logic and functions.

**AppTest.kt**: Contains unit tests for the various functions in App.kt.

