import Owner.ME
import Owner.NEUTRAL
import Owner.OPPONENT
import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val factoryCount = input.nextInt() // the number of factories
    val linkCount = input.nextInt() // the number of links between factories
    for (i in 0 until linkCount) {
        val factory1 = input.nextInt()
        val factory2 = input.nextInt()
        val distance = input.nextInt()
    }

    // game loop
    while (true) {

        val factories = mutableListOf<Factory>()
        val troops = mutableListOf<Troop>()

        val entityCount = input.nextInt() // the number of entities (e.g. factories and troops)
        for (i in 0 until entityCount) {
            val entityId = input.nextInt()
            val entityType = input.next()
            val arg1 = input.nextInt()
            val arg2 = input.nextInt()
            val arg3 = input.nextInt()
            val arg4 = input.nextInt()
            val arg5 = input.nextInt()

            when (entityType) {
                "FACTORY" -> factories += Factory(
                    id = entityId,
                    owner = arg1.toOwner(),
                    cyborgsNumber = arg2,
                    production = arg3
                )
                else -> troops += Troop(
                    id = entityId,
                    owner = arg1.toOwner(),
                    sourceId = arg2,
                    targetId = arg3,
                    cyborgsNumber = arg4,
                    remainingTurns = arg5
                )
            }

        }

        // System.err.println("factories : $factories")
        // System.err.println("troops : $troops")

        val pollenizeOrders = factories.pollenize()
        if (pollenizeOrders.isEmpty())
            println("WAIT")
        else
            println(pollenizeOrders)
    }
}

fun List<Factory>.pollenize(): String {
    return filter { it.owner == ME }
        .filter { it.cyborgsNumber > 5 }
        .flatMap { source ->
                filter { it.owner != ME }
                .filter { it.production > 0 }
                .map { destination ->
                    "MOVE ${source.id} ${destination.id} 1"
                }
        }.joinToString(separator = ";")
}

fun Int.toOwner() = when (this) {
        1 -> ME
        -1 -> OPPONENT
        else -> NEUTRAL
    }

enum class Owner { ME, OPPONENT, NEUTRAL }

data class Factory(
    val id: Int,
    val owner: Owner,
    val cyborgsNumber: Int,
    val production: Int
)

data class Troop(
    val id: Int,
    val owner: Owner,
    val sourceId: Int,
    val targetId: Int,
    val cyborgsNumber: Int,
    val remainingTurns: Int
)