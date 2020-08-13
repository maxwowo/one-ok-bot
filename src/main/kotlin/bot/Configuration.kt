package bot

import bot.specifications.BotSpecification
import com.uchuhimo.konf.Config

/**
 * Central class representing the bot's configuration, wrapping a Konf [Config] object
 *
 * Configuration is loaded from two locations, with values from earlier locations being overwritten by values in later
 * locations
 * 1) Environment variables defined at run-time
 * 2) System properties defined at run-time
 */
class Configuration {
    /* Configuration map */
    private var configuration = Config { addSpec(BotSpecification); }
        .from.env()
        .from.systemProperties()

    /* Bot login token */
    val token: String get() = configuration[BotSpecification.token]

    /* Bot command prefix */
    val prefix: String get() = configuration[BotSpecification.commandPrefix]
}

/**
 * The singleton [Configuration]
 *
 * You should always use this instead of constructing an instance of [Configuration] yourself
 */
val configuration = Configuration()
