package converter

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

class Converter {
    private fun convertIntegerPartToBase(integerNumber: String, base: Int): String {
        val result = mutableListOf<Char>()
        var dividend = BigInteger(integerNumber)
        do {
            result.add((dividend % base.toBigInteger()).toInt().digitToChar(base))
            dividend /= base.toBigInteger()
        } while (dividend != BigInteger.ZERO)
        return result.joinToString("").reversed()
    }

    private fun convertDecimalPartToBase(decimalNumber: String, base: Int): String {
        val result = mutableListOf<Char>()
        var multiplicand = if (decimalNumber.contains(".")) BigDecimal(decimalNumber).setScale(5)
        else BigDecimal("0.$decimalNumber").setScale(5)
        do {
            multiplicand *= BigDecimal(base)
            result.add(multiplicand.setScale(0, RoundingMode.FLOOR).toInt().digitToChar(base))
            multiplicand = multiplicand.remainder(BigDecimal.ONE)
        } while (multiplicand.remainder(BigDecimal.ONE) != BigDecimal(0) && result.size < 5)
        return result.joinToString("")
    }

    private fun convertIntegerPartToBase10(integerNumber: String, base: Int): String {
        var result = BigInteger.ZERO
        integerNumber.toCharArray().reversed().forEachIndexed { index, digit ->
            result += base.toBigInteger().pow(index) * digit.digitToInt(base).toBigInteger()
        }
        return result.toString()
    }

    private fun convertDecimalPartToBase10(decimalNumber: String, base: Int): String {
        var result = BigDecimal.ZERO
        decimalNumber.toCharArray().forEachIndexed { index, digit ->
            result += BigDecimal.ONE.setScale(5) / base.toBigDecimal().pow(index + 1) * digit.digitToInt(base)
                .toBigDecimal()
        }
        return result.toString()
    }

    fun convert(number: String, sourceBase: Int, targetBase: Int) {
        if (number.contains(".")) {
            val (integerPart, decimalPart) = number.split(".")
            val base10IntegerPart = convertIntegerPartToBase10(integerPart, sourceBase)
            val convertedIntegerPart = convertIntegerPartToBase(base10IntegerPart, targetBase)
            val base10DecimalPart = convertDecimalPartToBase10(decimalPart, sourceBase)
            val convertedDecimalPart = convertDecimalPartToBase(base10DecimalPart, targetBase)
            print("Conversion result: $convertedIntegerPart.$convertedDecimalPart\n\n")
        } else {
            val base10Number = convertIntegerPartToBase10(number, sourceBase)
            val convertedNumber = convertIntegerPartToBase(base10Number, targetBase)
            print("Conversion result: $convertedNumber\n\n")
        }
    }
}