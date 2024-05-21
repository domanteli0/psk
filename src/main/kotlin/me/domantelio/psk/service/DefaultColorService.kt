package me.domantelio.psk.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Default
import java.io.Serializable

// TODO: colors for light theme

@ApplicationScoped
@Default
class DefaultColorService() : Serializable, ColorService {
    override fun fromHashCode(obj: Any): String {
        with(Static) {
            val hash: UInt = shiftToUInt(obj.hashCode())
            val index = hash % (COLORS.size.toUInt())
            return COLORS[index.toInt()]
        }
    }

    object Static {
        val COLORS: List<String> = listOf(
            "#b3bdf9",
            "#c196f8",
            "#f4907b",
            "#c8d891",
            "#f7ca6f",
        )

        fun shiftToUInt(i: Int): UInt {
            return if (i < 0) {
                (i + (Int.MAX_VALUE + 1)).toUInt()
            } else {
                (i.toUInt() + (UInt.MAX_VALUE.shr(1))) + 1u
            }
        }
    }
}

