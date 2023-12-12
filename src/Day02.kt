fun main() {

    data class Draw(val r: Int, val g: Int, val b: Int)
    data class Game(val id: Int, val draws: List<Draw>) {
        fun isGamePossible(rLimit: Int, bLimit: Int, gLimit: Int): Boolean {
            val maxR = draws.maxOfOrNull { it.r }
            val maxG = draws.maxOfOrNull { it.g }
            val maxB = draws.maxOfOrNull { it.b }

            return if (maxR != null && maxG != null && maxB != null) {
                maxR <= rLimit && maxG <= gLimit && maxB <= bLimit
            } else {
                false
            }
        }
    }

    fun lineToGame(line: String): Game {
        val idAndDraws = line.split(":")
        val gameId = idAndDraws[0].split(" ")[1].toInt()

        val draws = idAndDraws[1].split(";").map { d ->
            d.split(",")
                .map { it.trim().split(" ") }
                .map { it[1] to it[0].toInt() }
        }
            .map { it.toMap() }
            .map { d ->
                Draw(
                    d.getOrDefault("red", 0),
                    d.getOrDefault("green", 0),
                    d.getOrDefault("blue", 0)
                )
            }

        return Game(gameId, draws)
    }

    fun powerDraw(draws: List<Draw>): Int {
        return draws.maxOf { it.r } * draws.maxOf { it.g } * draws.maxOf { it.b }
    }

    fun part1(input: List<String>): Int {
        val games = input.map { lineToGame(it) }

        return games.filter { it.isGamePossible(12, 14, 13) }.sumOf { it.id }
    }

    fun part2(input: List<String>): Int {
        val games = input.map { lineToGame(it) }

        return games.fold(0) { acc, game ->
            acc + powerDraw(game.draws)
        }
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

    val test2Input = readInput("Day02_test_part2")

    check(part2(test2Input) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}