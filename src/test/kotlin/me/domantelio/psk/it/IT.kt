package it.me.domantelio.psk

import com.codeborne.selenide.Selenide.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selectors.*
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.Selenide.`$` as s
import org.junit.jupiter.api.AfterAll
import org.openqa.selenium.chrome.ChromeOptions
import kotlin.random.Random

//@ExtendWith(ArquillianExtension::class)
class IT {

    companion object {
        var rng: Random = Random(System.currentTimeMillis())

        @JvmStatic
        @BeforeAll
        fun setUpAll() {
            Configuration.browserSize = "1280x800"
            Configuration.browserCapabilities = ChromeOptions().apply {
//                addArguments("--headless")
            }
        }

        @JvmStatic
        @AfterAll
        fun cleanup() {
//            closeWindow()
//            closeWebDriver()
        }

        @BeforeEach
        fun setUp() {
//            // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
//            Configuration.browserCapabilities = ChromeOptions().addArguments("--remote-allow-origins=*")
            open("http://localhost:8080/")
        }
    }

    private val logger = LoggerFactory.getLogger(IT::class.java)

    @Test
    fun createInvoiceAddItemAddCategory() {
        val INVOICE_NAME  = "Test-invoice"
        val CATEGORY_NAME = "Test-category-${rng.nextLong()}"
        val ITEM_NAME = "Test-item"

        open("/index.xhtml")

        s("[id=\"invoiceForm:name\"]").setValue(INVOICE_NAME)
        s("[id=\"invoiceForm:submitInvoice\"]").click()

        s("[id=\"j_id_j:categoryName\"]").setValue(CATEGORY_NAME)
        s("[id=\"j_id_j:j_id_m\"]").click()

        Thread.sleep(100_000)
    }
}