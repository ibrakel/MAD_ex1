package at.ac.fhcampuswien

import java.util.Scanner

class App {
    // Lambda for generating a random non-repeating number
    val generateRandomNonRepeatingNumber: (Int) -> Int = { length ->
        if (length !in 1..9) throw IllegalArgumentException("Length must be between 1 and 9.")
        val digits = (1..9).shuffled().take(length).joinToString("").toInt()
        digits
    }

    // Lambda for checking the user's input against the generated number
    val checkUserInputAgainstGeneratedNumber: (Int, Int) -> CompareResult = { input, generatedNumber ->
        val inputStr = input.toString()
        val generatedStr = generatedNumber.toString()

        if (inputStr.length != generatedStr.length) throw IllegalArgumentException("Input and generated number must have the same number of digits.")

        var correctPositions = 0
        val inputDigitCounts = IntArray(10)
        val generatedDigitCounts = IntArray(10)

        for (i in inputStr.indices) {
            val inputDigit = inputStr[i] - '0'
            val generatedDigit = generatedStr[i] - '0'

            if (inputDigit == generatedDigit) {
                correctPositions++
            }

            inputDigitCounts[inputDigit]++
            generatedDigitCounts[generatedDigit]++
        }

        val correctDigits = (1..9).sumOf { digit ->
            minOf(inputDigitCounts[digit], generatedDigitCounts[digit])
        }

        CompareResult(correctDigits, correctPositions)
    }

    // Game logic for a number guessing game
    fun playNumberGame(digitsToGuess: Int = 4) {
        val generatedNumber = generateRandomNonRepeatingNumber(digitsToGuess)
        println("Welcome to the Number Guessing Game!")
        println("Guess a $digitsToGuess-digit number with non-repeating digits. Type 'exit' to quit.")

        val scanner = Scanner(System.`in`)
        var guess: Int
        var result: CompareResult

        do {
            println("Enter your guess:")
            val input = scanner.nextLine()
            if (input.equals("exit", ignoreCase = true)) {
                println("Exiting game...")
                return
            }

            guess = input.toIntOrNull() ?: 0
            result = checkUserInputAgainstGeneratedNumber(guess, generatedNumber)
            println("Your guess: $input -> ${result}")
        } while (result.m != digitsToGuess)

        println("Congratulations, you've guessed the number correctly: $generatedNumber")
    }
}

fun main() {
    val app = App()
    app.playNumberGame() // Call the game function with default argument
    // app.playNumberGame(6) // Example of calling the game with a different number of digits
}
