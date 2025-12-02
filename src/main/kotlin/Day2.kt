import utils.readInput
import kotlin.math.max


fun main() {
    val basicInput = readInput("day2-basic.txt") {lines -> mapDay2(lines)}
    val input = readInput("day2.txt") {lines -> mapDay2(lines)}
    println(handleDay2(basicInput))
    println(handleDay2(input))
    println(handleDay2(basicInput, basicInput.maxOf { it.toString().length }))
    println(handleDay2(input, basicInput.maxOf { it.toString().length }))
}

fun handleDay2(input: List<Pair<Long, Long>>, duplicationCount: Int = 1): Long {
    val generateValues = generateValues(input.maxOf { it.second }, duplicationCount)
    return input.fold(0L) { acc, (min, max) ->
        acc + generateValues.filter { it in min..max }.sum()
    }
}

fun generateValues(maxInput: Long, duplicationCount: Int = 1): Set<Long> {
    var current = 1L
    val maxValue = max(maxInput.toString().take(maxInput.toString().length/2).toLong(),
        maxInput.toString().drop(maxInput.toString().length/2).toLong())
    val values = mutableSetOf<Long>()
    while (current <= maxValue) {
        for (i in 1..duplicationCount) {
            val stringConcat = getStringConcat(current, i)
            if (stringConcat == null) break
            values.add(stringConcat)
        }
        current++
    }
    return values
}

fun getStringConcat(input: Long, duplicationCount: Int): Long? {
    var stringConcat = input.toString()
    return try {
        for ( i in 0 until duplicationCount) stringConcat += input.toString()
        return stringConcat.toLong()
    } catch (e: NumberFormatException) {
        null
    }
}

private fun mapDay2(lines: List<String>): List<Pair<Long, Long>> = {
    val regex = Regex("""(\d+)-(\d+)""")
    lines[0].split(",").map { split ->
        val findAll = regex.find(split)
        Pair(findAll!!.groups[1]!!.value.toLong(), findAll.groups[2]!!.value.toLong())
    }
}()
