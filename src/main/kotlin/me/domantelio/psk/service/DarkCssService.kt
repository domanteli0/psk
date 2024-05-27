package me.domantelio.psk.service

import jakarta.enterprise.context.RequestScoped
import jakarta.enterprise.inject.Alternative
import jakarta.enterprise.inject.Specializes
import org.apache.ibatis.type.Alias

@Specializes
@RequestScoped
class DarkCssService() : CssService() {
    override fun getCssTheme(): String {
        return "dark.css"
    }
}