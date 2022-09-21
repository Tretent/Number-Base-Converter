package converter // Do not delete this line

fun main() {
    print("Enter number in decimal system: ")
    val decimal = readln().toInt()
    print("Enter target base: ")
    val base = readln().toInt()

    print("Conversion result: ${convert(decimal, base)}")
}

fun convert(decimalNumber: Int, base: Int): String = when (base) {
    2 -> decimalNumber.toString(base)
    8 -> String.format("%o", decimalNumber)
    16 -> Integer.toHexString(decimalNumber)
    else -> "invalid base"
}
