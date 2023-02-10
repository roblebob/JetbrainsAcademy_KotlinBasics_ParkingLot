fun iterator(map: Map<String, Int>) {
    // put your code here
    for ((k, v) in map) {
        println(
            when {
                v % 3 == 0 -> "Fizz"
                v % 5 == 0 -> "Buzz"
                else -> "FizzBuzz"
            }
        )
    }
}