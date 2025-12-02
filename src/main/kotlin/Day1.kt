import utils.readInput
import kotlin.math.abs


fun main() {
    val basicInput = readInput("day1-basic.txt") {lines -> mapValues(lines)}
    val input = readInput("day1.txt") {lines -> mapValues(lines)}
    println(handleDay1(basicInput) { _, newValue, _ -> if (newValue == 0) 1 else 0  })
    println(handleDay1(input) { _, newValue, _ -> if (newValue == 0) 1 else 0  })
    println(handleDay1(basicInput, calculateCount()))
    println(handleDay1(input, calculateCount()))
}

private fun calculateCount(): (Int, Int, Int) -> Int = { initial, newValue, movement ->
    val fullRotations = movement / 100
    if ((initial + movement - fullRotations * 100 != newValue || newValue == 0) && initial != 0) abs(fullRotations) + 1 else abs(fullRotations)
}

fun handleDay1(basicInput: List<Int>, countClick: (currentValue: Int, newValue: Int, movement: Int) -> Int): Int {
    val maxValue = 100
    var startingValue = 50
    var count = 0
    basicInput.forEach { value, ->
        val initial = startingValue
        startingValue = (((startingValue + value) % maxValue) + maxValue) % maxValue
        count += countClick(initial, startingValue, value)
//        println("$initial -> $startingValue ($value) ${countClick(initial, startingValue, value)}")
    }
    return count
}

private fun mapValues(lines: List<String>): List<Int> = lines.map { line ->
    val regex = Regex("""(\d+)""")
    when {
        "L" in line -> -(regex.find(line)?.value?.toInt() ?: 0)
        else -> regex.find(line)?.value?.toInt() ?: 0
    }
}
