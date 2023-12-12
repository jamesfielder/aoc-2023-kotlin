fun main() {
    fun part1(input: List<String>): Int {
        val digits = input.map { it.split("") }.map { it.removeEmptyStrings() }.map { it.selectDigits() }

        return digits.map { (it.first() * 10) + (it.last()) }.sum()
    }

    fun part2(input: List<String>): Int {
        val fixedDigits = input.map { it.addDigitsToWords() }

        return part1(fixedDigits)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val test2Input = readInput("Day01_test_part2")
    check(part2(test2Input) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
