package me.domantelio.psk.service

import jakarta.enterprise.context.ApplicationScoped
import java.io.Serializable
import kotlin.random.Random

// TODO: colors for light theme

@ApplicationScoped
class ColorService() : Serializable {
    fun fromHashCode(obj: Any): String {
        with(Static) {
            val index: Int = obj.hashCode() % COLORS.size
            return COLORS[index]
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
    }
}

