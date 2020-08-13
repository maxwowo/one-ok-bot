package bot

import bot.configurations.build
import bot.configurations.configuration
import mu.KotlinLogging

fun main() {
    val logger = KotlinLogging.logger {}

    logger.info { "Starting One Ok Bot version ${build.version}." }

    print(configuration.token)
}
