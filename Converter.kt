package converter

import kotlin.math.pow

class Converter {
    private fun convertToBase(decimalNumber: Int, base: Int): String {
//        elegant but "illegal" system
//        return when (base) {
//            2 -> decimalNumber.toString(base)
//            8 -> String.format("%o", decimalNumber)
//            16 -> Integer.toHexString(decimalNumber).uppercase()
//            else -> "Invalid base"
//        }
        val result = mutableListOf<Char>()
        var dividend = decimalNumber
        while (dividend != 0) {
            result.add((dividend % base).digitToChar(base))
            dividend /= base
        }
        return result.joinToString("").reversed()
    }

    private fun convertToDecimal(number: String, base: Int): String {
        var result = 0
        number.toCharArray().reversed().forEachIndexed { index, digit ->
            result += (base.toDouble().pow(index) * digit.digitToInt(base)).toInt()
        }
        return result.toString()
    }

    fun from() {
        print("Enter number in decimal system: ")
        val decimal = readln().toInt()
        print("Enter target base: ")
        val base = readln().toInt()

        print("Conversion result: ${convertToBase(decimal, base)}\n\n")
    }

    fun to() {
        print("Enter source number: ")
        val number = readln()
        print("Enter source base: ")
        val base = readln().toInt()

        print("Conversion to decimal result: ${convertToDecimal(number, base)}\n\n")
    }
}