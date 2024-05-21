package me.domantelio.psk.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.RequestScoped
import jakarta.enterprise.inject.Alternative

interface ColorService {
    fun fromHashCode(obj: Any): String
}

