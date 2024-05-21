package me.domantelio.psk.service

import jakarta.enterprise.context.RequestScoped
import java.io.Serializable

@RequestScoped
open class CssService() : Serializable {
    open fun getCssTheme(): String {
        return "light.css"
    }
}