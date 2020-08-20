package bot

import bot.configurations.buildProperties
import io.sentry.Sentry
import mu.KotlinLogging

fun main() {
    val bot = Bot()
    val logger = KotlinLogging.logger {}

    logger.info { "Starting One Ok Bot version ${buildProperties.version}." }

    Sentry.init()

    bot.run()
}
