package bot.configurations

import bot.configurations.specifications.AudioSpec
import bot.configurations.specifications.BotSpec
import bot.configurations.specifications.ImageSpec
import com.uchuhimo.konf.Config
import com.uchuhimo.konf.Feature
import com.uchuhimo.konf.source.yaml

/**
 * Central class representing the bot's configuration, wrapping a Konf [Config] object
 *
 * Configuration is loaded from two locations, with values from earlier locations being overwritten by values in later
 * locations
 * 1) The default.yml file found in resources
 * 2) Environment variables defined at run-time
 * 3) System properties defined at run-time
 */
class Configurations {
    // Configuration map
    private var configurations =
        Config {
            addSpec(BotSpec)
            addSpec(AudioSpec)
            addSpec(ImageSpec)
        }
            .from.enabled(Feature.FAIL_ON_UNKNOWN_PATH).yaml.resource("default.yml")
            .from.env()
            .from.systemProperties()

    // Bot login token
    val token: String get() = configurations[BotSpec.token]

    // Bot command prefix
    val prefix: String get() = configurations[BotSpec.commandPrefix]

    // ID of the bot's owner
    val owner: String get() = configurations[BotSpec.owner]

    // Command to ask for help
    val helpCommand: String get() = configurations[BotSpec.helpCommand]

    // Link to the lo-fi beats stream
    val lofiURL: String get() = configurations[AudioSpec.lofiURL]

    // Link to a One Ok Rock playlist
    val rockURL: String get() = configurations[AudioSpec.rockURL]

    // Link to No Scared
    val noScaredURL: String get() = configurations[AudioSpec.noScaredURL]

    // Link to Hip To Be Square
    val hipToBeSquareURL: String get() = configurations[AudioSpec.hipToBeSquareURL]

    // Link to Hip To Be Square
    val tutelURL: String get() = configurations[AudioSpec.tutelURL]

    // Link to the picture of Patrick Bateman holding an axe
    val batemanAxeURL: String get() = configurations[ImageSpec.batemanAxeURL]
}

/**
 * The singleton [Configurations]
 *
 * This should always be used instead of constructing an instance of [Configurations] manually
 */
val configurations = Configurations()
