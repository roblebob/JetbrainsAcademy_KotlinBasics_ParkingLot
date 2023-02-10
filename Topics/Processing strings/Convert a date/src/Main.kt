fun main() {
    // write your code here
    val s = readln()
    val l = s.split("-")
    val new = listOf(l[1], l[2], l[0])
    println(new.joinToString("/"))
}