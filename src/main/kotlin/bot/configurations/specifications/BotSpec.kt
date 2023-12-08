package bot.configurations.specifications

import com.uchuhimo.konf.ConfigSpec

/**
 * A class which stores the bot specifications
 *
 * This is used by Konf and will not be accessed externally
 */
object BotSpec : ConfigSpec() {
    // Bot login token
    val token by required<String>(description = "Bot login token")

    // Character/s required before commands
    val commandPrefix by required<String>(name = "prefix", description = "Command prefix")

    // ID of the bot's owner
    val owner by required<String>(description = "Discord ID of the bot's owner")

    // Command to ask for help
    val helpCommand by required<String>(description = "Command to ask for help")
}
