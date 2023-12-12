import java.math.BigInteger
import java.security.MessageDigest
import java.util.regex.Pattern
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun <T> T.printlnrtn(): T {
    println(this)
    return this
}

fun List<String>.removeEmptyStrings() = this.filter { it.isNotBlank() }

fun List<String>.selectDigits(): List<Int> = this.map { it.toCharArray()[0] }
    .filter { it.isDigit() }
    .map { it.digitToInt() }

val wordsToDigitsMap = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

fun String.addDigitsToWords(): String {
    return wordsToDigitsMap.keys.fold(this) {
            acc, iter -> acc.replace(iter, iter + wordsToDigitsMap[iter].toString() + iter)
    }
}