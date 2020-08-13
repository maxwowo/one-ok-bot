package bot.configurations.specifications

import com.uchuhimo.konf.ConfigSpec

/**
 * A class which stores the bot configuration
 *
 * This is used by Konf and will not be accessed externally
 */
object BotSpec : ConfigSpec() {
    /* Bot login token */
    val token by required<String>(description = "Bot login token")

    /* Character/s required before commands */
    val commandPrefix by required<String>(name = "prefix", description = "Command prefix")
}
