package utils

fun generateMasks(n: Int, k: Int): List<List<Boolean>> {
    val results = ArrayList<BooleanArray>()

    val comb = IntArray(k) { it }

    while (true) {
        val mask = BooleanArray(n)
        for (i in 0 until k) mask[comb[i]] = true
        results.add(mask)

        var i = k - 1
        while (i >= 0 && comb[i] == n - k + i) i--
        if (i < 0) break

        comb[i]++
        for (j in i + 1 until k) {
            comb[j] = comb[j - 1] + 1
        }
    }

    return results.map { it.toList() }
}
