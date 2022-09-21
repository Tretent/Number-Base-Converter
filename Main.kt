package converter // Do not delete this line

fun main() {
    val converter = Converter()
    while (true) {
        print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ")
        when (readln()) {
            "/from" -> converter.from()
            "/to" -> converter.to()
            "/exit" -> break
            else -> println("Invalid command")
        }
    }
}
