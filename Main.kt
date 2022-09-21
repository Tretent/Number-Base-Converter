package converter

fun main() {
    val converter = Converter()
    initialPrompt(converter)
}

fun initialPrompt(converter: Converter) {
    while (true) {
        print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ")
        when (val selection = readln()) {
            "/exit" -> return
            else -> {
                val (sourceBase, targetBase) = selection.split(" ").map { it.toInt() }
                convertPrompt(converter, sourceBase, targetBase)
            }
        }
    }
}

fun convertPrompt(converter: Converter, sourceBase: Int, targetBase: Int) {
    while (true) {
        print("Enter number in base $sourceBase to convert to base $targetBase (To go back type /back) ")
        when (val selection = readln()) {
            "/back" -> println().also { return }
            else -> converter.convert(selection, sourceBase, targetBase)
        }
    }
}
