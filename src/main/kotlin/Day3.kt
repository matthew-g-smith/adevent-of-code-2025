import utils.readInput


fun main() {
    val basicInput = readInput("day3-basic.txt") {lines -> mapDay3(lines)}
    val input = readInput("day3.txt") {lines -> mapDay3(lines)}
    println(handleDay3(basicInput))
    println(handleDay3(input))
    println(handleDay3(basicInput, 12))
    println(handleDay3(input, 12))
}

fun handleDay3(input: List<List<Long>>, size: Int = 2): Long {
    return input.fold(0L) { acc, line ->
        acc + findMaxDay3(line, size)
    }
}

fun findMaxDay3(line: List<Long>, size: Int = 2): Long {
    val data = line
    val values = mutableListOf<Long>()
    for (i in 0 until size) values.add(0)
    for (i in 0 until line.size) shiftValuesIfNeeded(data[i], values, 0, i, line.size)
    return values.joinToString("").toLong()
}

fun shiftValuesIfNeeded(lng: Long, values: MutableList<Long>, index: Int, inputIndex: Int, size: Int) {
    for (valueIndex in 0 until values.size) {
        if (size - inputIndex >= values.size - valueIndex && values[valueIndex] < lng) {
            values[valueIndex] = lng
            for (otherIndex in valueIndex + 1 until values.size) values[otherIndex] = 0
            break
        }
    }
}


private fun mapDay3(lines: List<String>): List<List<Long>> = {
    lines.map { line ->
        line.split("").filter { it.isNotBlank() }.map { it.toLong() }
    }
}()
