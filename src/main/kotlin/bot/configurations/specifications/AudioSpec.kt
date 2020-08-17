package bot.configurations.specifications

import com.uchuhimo.konf.ConfigSpec

/**
 * A class which stores the audio specifications
 *
 * This is used by Konf and will not be accessed externally
 */
object AudioSpec : ConfigSpec() {
    /* Link to the lo-fi beats stream */
    val lofiURL by required<String>(description = "Link to the lo-fi beats stream")
}
