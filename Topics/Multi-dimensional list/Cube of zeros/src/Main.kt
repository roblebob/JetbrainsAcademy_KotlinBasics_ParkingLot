const val N = 3

fun main() {
    // write your code here
    println(MutableList(N) { MutableList(N) { MutableList(N) { 0 } } })
}