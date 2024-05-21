package me.domantelio.psk.faces

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.transaction.Transactional
import me.domantelio.psk.entity.Item
import me.domantelio.psk.repositoy.ItemRepository
import me.domantelio.psk.service.CssService
import java.io.Serializable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.w3c.dom.css.CSSCharsetRule

@Named
@RequestScoped
open class IndexFace() : Serializable {

    @Inject
    private lateinit var css: CssService

    open fun getCss() : String =
        css.getCssTheme()

}
