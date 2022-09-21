package converter

import java.math.BigDecimal
import java.math.BigInteger

class Converter {
    private fun convertToBase(decimalNumber: String, base: Int): String {
        val result = mutableListOf<Char>()
        var dividend = BigInteger(decimalNumber)
        do {
            result.add((dividend % base.toBigInteger()).toInt().digitToChar(base))
            dividend /= base.toBigInteger()
        } while (dividend != BigInteger.ZERO)
        return result.joinToString("").reversed()
    }

    private fun convertToDecimal(number: String, base: Int): String {
        var result = BigDecimal.ZERO
        number.toCharArray().reversed().forEachIndexed { index, digit ->
            result += base.toBigDecimal().pow(index) * digit.digitToInt(base).toBigDecimal()
        }
        return result.toBigInteger().toString()
    }

    fun convert(number: String, sourceBase: Int, targetBase: Int) {
        val decimalNumber = convertToDecimal(number, sourceBase)
        val convertedNumber = convertToBase(decimalNumber, targetBase)
        print("Conversion result: $convertedNumber\n\n")
    }
}