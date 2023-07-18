fun main() {
    respond(Phone("Mobile"))
    respond(Television("Living Room"))
    var kunai = Weapon("Kunai")
    kunai.attack()
}

interface Gadget {
    val name: String
    fun respond()
}

class Television(
    override val name:String
) : Gadget {
    override fun respond() {
        println("Opening the ${this.name.lowercase()} TV to play a movie")
    }
}

class Phone(
    override val name:String
) : Gadget {
    override fun respond() {
        println("Performing calls using ${this.name.lowercase()}")
    }
}

class Weapon(val name:String) {
    fun attack(){
        println("Attacking with ${this.name}")
    }
}
fun respond(device :Gadget) {
    device.respond()
}