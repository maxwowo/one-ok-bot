package bot.configurations.specifications

import com.uchuhimo.konf.ConfigSpec

/**
 * A class which stores the image specifications
 *
 * This is used by Konf and will not be accessed externally
 */
object ImageSpec : ConfigSpec() {
    // Link to the picture of Patrick Bateman holding an axe
    val batemanAxeURL by required<String>(description = "Link to the picture of Patrick Bateman holding an axe")
}
