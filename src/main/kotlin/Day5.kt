import utils.readInput
import java.util.Comparator


fun main() {
    val basicInput = readInput("day5-basic.txt") {lines -> mapDay5(lines)}
    val input = readInput("day5.txt") {lines -> mapDay5(lines)}
    println(handleDay5(basicInput))
    println(handleDay5(input))
    println(handleDay5Part2(basicInput))
    println(handleDay5Part2(input))
}

fun handleDay5Part2(input: Pair<MutableList<Range>, MutableList<Long>>): Long {
    return input.first.fold(0L) {acc, range -> acc + range.end - range.start + 1}
}

fun handleDay5(input: Pair<MutableList<Range>, MutableList<Long>>): Long {
    return input.second.count { value ->
        input.first.any { range -> value in range.start..range.end }
    }.toLong()
}



private fun mapDay5(lines: List<String>): Pair<MutableList<Range>, MutableList<Long>> = {
    val ranges = mutableListOf<Range>()
    val values = mutableListOf<Long>()
    val regex = Regex("""(\d+)-(\d+)""")
    lines.forEach { line ->
        when {
            "-" in line -> {
                val findAll = regex.find(line)
                ranges.add(Range(findAll!!.groups[1]!!.value.toLong(), findAll.groups[2]!!.value.toLong()))
            }
            line.isNotBlank() -> values.add(line.toLong())
        }
    }
    Pair(combineRanges(ranges), values)
}()

fun combineRanges(ranges: MutableList<Range>): MutableList<Range> {
    if (ranges.isEmpty()) return ranges

    ranges.sortBy { it.start }

    val merged = mutableListOf<Range>()
    var current = ranges[0]

    for (i in 1 until ranges.size) {
        val next = ranges[i]

        if (current.end >= next.start - 1) {
            current = current.copy(end = maxOf(current.end, next.end))
        } else {
            merged.add(current)
            current = next
        }
    }

    merged.add(current)
    return merged
}

data class Range(var start: Long, var end: Long)
