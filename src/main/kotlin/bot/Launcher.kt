package bot

import bot.configurations.build
import mu.KotlinLogging

fun main() {
    val bot = Bot()
    val logger = KotlinLogging.logger {}

    logger.info { "Starting One Ok Bot version ${build.version}." }

    bot.run()
}
