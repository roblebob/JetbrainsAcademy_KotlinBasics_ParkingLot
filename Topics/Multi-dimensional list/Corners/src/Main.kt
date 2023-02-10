fun main() {
    // Do not touch code below
    val inputList: MutableList<MutableList<String>> = mutableListOf()
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val strings = readLine()!!.split(' ').toMutableList()
        inputList.add(strings)
    }
    // write your code here
    val outputList = mutableListOf(
        mutableListOf(inputList.first().first(), inputList.first().last()),
        mutableListOf(inputList.last().first(), inputList.last().last())
    )
    println(outputList.map { it.joinToString(" ") }.joinToString("\n"))
}