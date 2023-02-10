package parking

const val NUM_PARKING_SPOTS = 20


fun main() {

    val spotsAvailable = MutableList(NUM_PARKING_SPOTS) {true}


    while (true) {
        val command = readln().split(" ");

        when (command[0]) {
            "park" -> {
                val spotIdx = spotsAvailable.indexOf(true)
                if (spotIdx == -1) {
                    println("Sorry, the parking lot is full.")
                    continue
                }
                spotsAvailable[spotIdx] = false
                val color = command[2]
                println("$color car parked in spot ${spotIdx + 1}.")
            }
            "leave" -> {
                val spotIdx = command[1].toInt() - 1

                if (!spotsAvailable[spotIdx]) {
                    spotsAvailable[spotIdx] = true
                    println("Spot ${spotIdx + 1} is free.")
                } else {
                    println("There is no car in spot ${spotIdx + 1}.")
                }
            }
            "exit" -> break
        }
    }
}

