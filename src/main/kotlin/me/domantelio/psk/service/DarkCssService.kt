package me.domantelio.psk.service

import jakarta.enterprise.context.RequestScoped
import jakarta.enterprise.inject.Specializes

@Specializes
@RequestScoped
class DarkCssService() : CssService() {
    override fun getCssTheme(): String {
        return "dark.css"
    }
}