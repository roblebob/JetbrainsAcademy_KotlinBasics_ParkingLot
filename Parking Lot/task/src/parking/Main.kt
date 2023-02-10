package parking

const val NUM_PARKING_SPOTS = 20


fun main() {

    val parkingLots = ParkingLots()


    while (true) {
        val command = readln().split(" ");

        when (command[0]) {
            "create" -> println(parkingLots.create(command[1].toInt()))
            "park" -> println(parkingLots.park(command[1], command[2]))
            "leave" -> println(parkingLots.leave(command[1].toInt()))
            "status" -> println(parkingLots.status())
            "reg_by_color" -> println(parkingLots.reg_by_color(command[1]))
            "spot_by_color" -> println(parkingLots.spot_by_color(command[1]))
            "spot_by_reg" -> println(parkingLots.spot_by_reg(command[1]))
            "exit" -> break
        }
    }
}

class ParkingLots{
    val lots: MutableList<ParkingLot> = mutableListOf()
    fun create(n: Int): String {
        if (n > 0) {
            lots.clear()
            lots.addAll(MutableList(n) {ParkingLot()})
            return "Created a parking lot with $n spots."
        }
        return "Error!!!  The number of spots should be positive!"
    }

    fun park(registration: String, color: String): String {

        if (lots.size == 0) {
            return "Sorry, a parking lot has not been created."
        }

        val spotsAvalaible = lots.map { it.available }
        val idx = spotsAvalaible.indexOf(true)

        if (idx == -1) {
            return "Sorry, the parking lot is full."
        }

        lots[idx].registration = registration
        lots[idx].color = color

        return "$color car parked in spot ${idx + 1}."
    }

    fun leave(pos: Int): String {
        if (lots.size == 0) {
            return "Sorry, a parking lot has not been created."
        }

        val idx = pos - 1

        return if (!lots[idx].available) {
            lots[idx].registration = ""
            lots[idx].color = ""
            "Spot $pos is free."
        } else {
            "There is no car in spot $pos."
        }
    }

    fun status(): String {
        if (lots.size == 0) {
            return "Sorry, a parking lot has not been created."
        }

        if (this.isEmpty()) {
            return "Parking lot is empty."
        }
        var res = ""
        val iter = lots.iterator()
        for ((idx, lot) in iter.withIndex()) {
            if (!lot.available) {
                res += "${idx + 1} ${lot.registration} ${lot.color}\n"
            }
        }
        return res.dropLast(1)
    }

    private fun isEmpty(): Boolean {
        var count = 0
        lots.map { if(!it.available) { count++} }
        return count == 0
    }


    fun reg_by_color(color: String): String {
        if (lots.size == 0) {
            return "Sorry, a parking lot has not been created."
        }
        val list = lots.filter { it.color.lowercase() == color.lowercase() }.map { it.registration }
        if (list.isEmpty()) {
            return "No cars with color $color were found."
        }
        return list.joinToString(", ")
    }

    fun spot_by_color(color: String): String {
        if (lots.size == 0) {
            return "Sorry, a parking lot has not been created."
        }
        val list = lots.map { it.color.lowercase() }.indicesOf(color.lowercase()).map { it + 1 }
        if (list.isEmpty()) {
            return "No cars with color $color were found."
        }
        return list.joinToString(", ")
    }

    fun spot_by_reg(registration: String): String {
        if (lots.size == 0) {
            return "Sorry, a parking lot has not been created."
        }
        val list = lots.map { it.registration }.indicesOf(registration).map { it + 1 }
        if (list.isEmpty()) {
            return "No cars with registration number $registration were found."
        }
        return list.joinToString(", ")
    }
}



class ParkingLot {
    var registration: String = ""
    var color: String = ""
    val available: Boolean
        get() = registration.isEmpty() && color.isEmpty()
}

fun <E> Iterable<E>.indicesOf(e: E)
        = mapIndexedNotNull{ index, elem -> index.takeIf{ elem == e } }