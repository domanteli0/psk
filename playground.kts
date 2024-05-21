import kotlin.random.Random
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

Json.decodeFromString<Unit>("")

val iMax = Int.MAX_VALUE

val negativeRand = Random.nextInt(Int.MIN_VALUE, 0)
val positiveRand = Random.nextInt(0, Int.MAX_VALUE)

println("negative = $negativeRand, positive = $positiveRand")


println("negative shifted: ${shiftToUInt(negativeRand)}")
println("positive shifted: ${shiftToUInt(positiveRand)}")
println("")
println("INT MAX: ${Int.MAX_VALUE}")
println("0 shifted: ${shiftToUInt(0)}")
println("")
println("Uint MAX: ${UInt.MAX_VALUE}")
println("max shifted: ${shiftToUInt(Int.MAX_VALUE)}")
println("")
println("min shifted: ${shiftToUInt(Int.MIN_VALUE)}")
