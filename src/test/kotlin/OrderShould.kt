import Owner.ME
import Owner.NEUTRAL
import Owner.OPPONENT
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class OrderShould {

    @Test
    internal fun `pollenize other bases`() {
        val factories = listOf(
            Factory(id = 1, owner = ME, cyborgsNumber = 10, production = 2),
            Factory(id = 2, owner = OPPONENT, cyborgsNumber = 10, production = 2),
            Factory(id = 3, owner = NEUTRAL, cyborgsNumber = 10, production = 2)
        )

        factories.pollenize() shouldBe "MOVE 1 2 1;MOVE 1 3 1"
    }
}