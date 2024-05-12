package me.domantelio.psk.test

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlInput
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput
import com.gargoylesoftware.htmlunit.html.HtmlTable
import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit5.ArquillianExtension
import org.jboss.shrinkwrap.api.ShrinkWrap.create
import org.jboss.shrinkwrap.api.importer.ZipImporter
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.io.File
import java.net.URL


@ExtendWith(ArquillianExtension::class)
class IT {
    private val webUrl: URL = URL("http://localhost:8080")

    private lateinit var webClient: WebClient

    @BeforeEach
    fun setUp() {
        webClient = WebClient()
    }

    @AfterEach
    fun tearDown() {
        webClient.close()
    }

//    companion object {
//        @Deployment(testable = false)
//        fun createDeployment(): WebArchive {
//            return create(ZipImporter::class.java, "psk.war")
//                .importFrom(File("target/psk.war"))
//                .`as`(WebArchive::class.java)
//        }
//    }
    object Config {
        private val WARNAME: String = System.getProperty("arquillian.war.name")

        @Deployment(testable = true)
        fun createDeployment(): WebArchive {
            val archive = create(WebArchive::class.java, WARNAME)
                .addPackages(true, "me.domantelio.psk")
            return archive
        }
    }

    @Test
    fun test() {
        // Navigate to the Home page
        val page: HtmlPage = webClient.getPage("${webUrl}/index.xhtml")
        // Get input text 'name' and set a value

        val name: HtmlInput = page.getElementById("formpanel:name") as HtmlInput
        name.valueAttribute = "john"
        // Get input text 'surname' and set a value
        val surname: HtmlInput = page.getElementById("formpanel:surname") as HtmlInput
        surname.valueAttribute = "smith"
        // Get input text 'email' and set a value
        val email: HtmlInput = page.getElementById("formpanel:email") as HtmlInput
        email.valueAttribute = "john@gmail.com"
        val submit: HtmlSubmitInput = page.getElementById("formpanel:submit") as HtmlSubmitInput
        // Get a new HtmlPage after submitting
        val page2: HtmlPage = submit.click()
        // Iterate on the Table
        val table: HtmlTable = page2.getHtmlElementById("datatableform:mytable")
        for (row in table.rows) {
            println("Found row")
            for (cell in row.cells) {
                println("   Found cell: " + cell.asNormalizedText())
            }
        }
    }

    @Test
    fun createInvoiceAddItemAddCategory() {
        val page: HtmlPage = webClient.getPage("${webUrl}/index.xhtml")

        val categoryName = page.getElementById("")

        val name: HtmlInput = page.getElementById("formpanel:name") as HtmlInput
        name.valueAttribute = "john"
        // Get input text 'surname' and set a value
        val surname: HtmlInput = page.getElementById("formpanel:surname") as HtmlInput
        surname.valueAttribute = "smith"
        // Get input text 'email' and set a value
        val email: HtmlInput = page.getElementById("formpanel:email") as HtmlInput
        email.valueAttribute = "john@gmail.com"
        val submit: HtmlSubmitInput = page.getElementById("formpanel:submit") as HtmlSubmitInput
        // Get a new HtmlPage after submitting
        val page2: HtmlPage = submit.click()
        // Iterate on the Table
        val table: HtmlTable = page2.getHtmlElementById("datatableform:mytable")
        for (row in table.rows) {
            println("Found row")
            for (cell in row.cells) {
                println("   Found cell: " + cell.asNormalizedText())
            }
        }
    }
}