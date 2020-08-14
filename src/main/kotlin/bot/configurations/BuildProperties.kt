package bot.configurations

import java.util.Properties

/**
 * Class that stores information about the bot's current build
 *
 * Build information is stored in build.properties in the resources directory
 */
class BuildProperties {
    private val properties = Properties()

    /* Current bot version */
    val version: String by lazy {
        properties.getProperty("version")
    }

    /* Load the build properties */
    fun load(): BuildProperties {
        properties.load(Thread.currentThread().contextClassLoader.getResourceAsStream("build.properties"))

        return this
    }
}

/**
 * The singleton [BuildProperties]
 *
 * You should always use this instead of constructing an instance of [BuildProperties] yourself
 */
val build = BuildProperties().load()
