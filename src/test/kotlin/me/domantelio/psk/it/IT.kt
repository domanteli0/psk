package it.me.domantelio.psk

import com.codeborne.selenide.Selenide.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import com.codeborne.selenide.Configuration
import org.assertj.core.api.Assertions.assertThat
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
                addArguments("--headless")
            }
        }

        @JvmStatic
        @AfterAll
        fun cleanup() {
            closeWindow()
            closeWebDriver()
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
    fun createInvoiceAddItem() {
        val INVOICE_NAME  = "test-invoice-${rng.nextLong()}"
        val ITEM_NAME = "test-item-${rng.nextLong()}"
        val ITEM_PRICE = rng.nextInt(1000).toString()
        val ITEM_DESC = "test-description-${rng.nextLong()}"

        open("/index.xhtml")

        s("[id=\"invoiceForm:name\"]").setValue(INVOICE_NAME)
        s("[id=\"invoiceForm:submitInvoice\"]").click()

        s("[id=\"j_id_q:itemName\"]").setValue(ITEM_NAME)
        s("[id=\"j_id_q:itemPrice\"]").setValue(ITEM_PRICE)
        s("[id=\"j_id_q:itemDescription\"]").setValue(ITEM_DESC)
        s("[id=\"j_id_q:j_id_x\"]").click()

        // Go to index
        s("body > a").click()

        var invoiceTable = s("#invoice-table")
        logger.debug("invoiceTable = ${invoiceTable.text}")
        assertThat(invoiceTable.text).contains(INVOICE_NAME)

        // Go to OTHERS
        s("body > a").click()

        val itemTable = s("#item-table")
        logger.debug("itemTable = ${itemTable.text}")
        assertThat(itemTable.text).contains(ITEM_NAME)
        assertThat(itemTable.text).contains(ITEM_DESC)
    }

    @Test
    fun createInvoiceAddCategory() {
        val INVOICE_NAME  = "test-invoice-${rng.nextLong()}"
        val CATEGORY_NAME = "test-category-${rng.nextLong()}"

        open("/index.xhtml")

        s("[id=\"invoiceForm:name\"]").setValue(INVOICE_NAME)
        s("[id=\"invoiceForm:submitInvoice\"]").click()

        s("[id=\"j_id_j:categoryName\"]").setValue(CATEGORY_NAME)
        s("[id=\"j_id_j:j_id_m\"]").click()

        // Set category off
        s("[id=\"j_id_c:j_id_e:0\"]").click()
        s("[id=\"j_id_c:j_id_h\"]").click()

        // Go to index
        s("body > a").click()

        var invoiceTable = s("#invoice-table")
        logger.debug("invoiceTable = ${invoiceTable.text}")
        assertThat(invoiceTable.text).contains(INVOICE_NAME)

        // Go to OTHERS
        s("body > a").click()

        var categoryTable =  s("#category-table")
        logger.debug("catTable = ${categoryTable.text}")
        assertThat(categoryTable.text).contains(CATEGORY_NAME)
    }

    @Test
    fun createInvoiceAddItemAddCategory() {
        val INVOICE_NAME  = "test-invoice-${rng.nextLong()}"
        val CATEGORY_NAME = "test-category-${rng.nextLong()}"
        val ITEM_NAME = "test-item-${rng.nextLong()}"
        val ITEM_PRICE = rng.nextInt(1000).toString()
        val ITEM_DESC = "test-description-${rng.nextLong()}"

        open("/index.xhtml")

        s("[id=\"invoiceForm:name\"]").setValue(INVOICE_NAME)
        s("[id=\"invoiceForm:submitInvoice\"]").click()

        s("[id=\"j_id_j:categoryName\"]").setValue(CATEGORY_NAME)
        s("[id=\"j_id_j:j_id_m\"]").click()

        s("[id=\"j_id_q:itemName\"]").setValue(ITEM_NAME)
        s("[id=\"j_id_q:itemPrice\"]").setValue(ITEM_PRICE)
        s("[id=\"j_id_q:itemDescription\"]").setValue(ITEM_DESC)
        s("[id=\"j_id_q:j_id_x\"]").click()

        // Set category off
        s("[id=\"j_id_c:j_id_e:0\"]").click()
        s("[id=\"j_id_c:j_id_h\"]").click()

        // Go to index
        s("body > a").click()

        var invoiceTable = s("#invoice-table")
        logger.debug("invoiceTable = ${invoiceTable.text}")
        assertThat(invoiceTable.text).contains(INVOICE_NAME)

        // Go to OTHERS
        s("body > a").click()

        var categoryTable =  s("#category-table")
        logger.debug("catTable = ${categoryTable.text}")
        assertThat(categoryTable.text).contains(CATEGORY_NAME)

        val itemTable = s("#item-table")
        logger.debug("itemTable = ${itemTable.text}")
        assertThat(itemTable.text).contains(ITEM_NAME)
        assertThat(itemTable.text).contains(ITEM_DESC)
    }
}