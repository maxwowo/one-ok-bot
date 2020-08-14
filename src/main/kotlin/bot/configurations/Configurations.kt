package bot.configurations

import bot.configurations.specifications.BotSpec
import com.uchuhimo.konf.Config
import com.uchuhimo.konf.Feature
import com.uchuhimo.konf.source.yaml

/**
 * Central class representing the bot's configuration, wrapping a Konf [Config] object
 *
 * Configuration is loaded from two locations, with values from earlier locations being overwritten by values in later
 * locations
 * 1) Environment variables defined at run-time
 * 2) System properties defined at run-time
 */
class Configurations {
    /* Configuration map */
    private var configurations = Config { addSpec(BotSpec); }
        .from.enabled(Feature.FAIL_ON_UNKNOWN_PATH).yaml.resource("default.yml")
        .from.env()
        .from.systemProperties()

    /* Bot login token */
    val token: String get() = configurations[BotSpec.token]

    /* Bot command prefix */
    val prefix: String get() = configurations[BotSpec.commandPrefix]

    /* ID of the bot's owner */
    val owner: String get() = configurations[BotSpec.owner]
}

/**
 * The singleton [Configurations]
 *
 * You should always use this instead of constructing an instance of [Configurations] yourself
 */
val configuration = Configurations()
