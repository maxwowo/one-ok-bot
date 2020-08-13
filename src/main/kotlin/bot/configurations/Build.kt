package bot.configurations

import java.util.Properties

/**
 * Class that stores information about the bot's current build
 *
 * Build information is stored in build.properties in the resources directory
 */
class Build {
    private val properties = Properties()

    /* Current bot version */
    val version: String by lazy {
        properties.getProperty("version")
    }

    /* Load the build information */
    fun load(): Build {
        properties.load(Thread.currentThread().contextClassLoader.getResourceAsStream("build.properties"))

        return this
    }
}

/**
 * The singleton [Build]
 *
 * You should always use this instead of constructing an instance of [Build] yourself
 */
val build = Build().load()
