package me.domantelio.psk.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Alternative
import java.io.Serializable

@ApplicationScoped
@Alternative
class BlueColorService() : Serializable, ColorService {
    override fun fromHashCode(obj: Any): String {
        with(Static) {
            val hash: UInt = shiftToUInt(obj.hashCode())
            val index = hash % (COLORS.size.toUInt())
            return COLORS[index.toInt()]
        }
    }

    object Static {
        val COLORS: List<String> = listOf(
            "#9dcbf9",
            "#8ba0e0",
            "#bff1ff",
            "#71b2e8"
        )

        fun shiftToUInt(i: Int): UInt {
            return if (i < 0) {
                (i + Int.MAX_VALUE + 1).toUInt()
            } else {
                (i.toUInt() + (UInt.MAX_VALUE.shr(1))) + 1u
            }
        }
    }
}

