package ghar.learn.kotlinreview


fun main() {


    // Use Extension function example from Utils class
    val inputString = "How  are     you library"
    println("input string: $inputString")
    println("output: ${inputString.replaceMultipleSpaces()}")
}

//fun replacementMultipleSpaces(value : String) : String {
//    val regex = Regex("\\s+")       // input with multiple spaces or tabs
//    return regex.replace(value, " ")
//}

// add above method to a String class
fun String.replaceMultipleSpaces() : String {
    val regex = Regex("\\s+")       // input with multiple spaces or tabs
    return regex.replace(this, " ")
}

var calories = 0

// Using backing field
val hasEaten : Boolean
    get() {
        return calories != 0            // return false if Person has not eaten anything, i.e: calories would be '0' then
    }

